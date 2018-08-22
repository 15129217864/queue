package com.xct.media.queuing.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xct.media.queuing.conf.ApplicationConf;
import com.xct.media.queuing.mapper.BaseBizMapper;
import com.xct.media.queuing.mapper.JobUserMapper;
import com.xct.media.queuing.mapper.SetBizScreenInfoMapper;
import com.xct.media.queuing.pojo.AdminUserPojo;
import com.xct.media.queuing.pojo.bo.JobUserBo;
import com.xct.media.queuing.service.JobUserService;
import com.xct.media.queuing.storage.StorageService;
import com.xct.media.queuing.util.DESPlusUtil;
import com.xct.media.queuing.util.RestApiMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/30 20:18.
 * @Description:
 */
@Service
public class JobUserServiceImpl implements JobUserService {

    private Logger logger = LoggerFactory.getLogger(JobUserServiceImpl.class);

    @Autowired
    private JobUserMapper jobUserMapper;

    @Autowired
    private BaseBizMapper baseBizMapper;

    @Autowired
    private SetBizScreenInfoMapper setBizScreenInfoMapper;

    @Autowired
    private ApplicationConf applicationConf;

    @Autowired
    private StorageService storageService;


    @Override
    public List<JobUserBo> queryAllJobUserInfo() {
        //查询出所有工作人员信息
        List<JobUserBo> jobUserList = jobUserMapper.queryJobUserSet(new JobUserBo());

        return jobUserList.stream()
                .map(jobuser -> {
                    String jobUserPhoto = jobuser.getJob_user_photo();
                    String serviceUrl = applicationConf.getService();
                    String requestUserPhoto = serviceUrl + "/common/biz/2/" + jobUserPhoto;
                    jobuser.setJob_user_photo(requestUserPhoto);
                    return jobuser;
                })
                .collect(toList());
    }

    @Override
    public List<JobUserBo> selectBaseJobUserInfo() {
        return jobUserMapper.queryJobUserSet(new JobUserBo());
    }

    @Override
    public boolean userLogin(String username, String password) {
        boolean flag = false;
        List<String> userJsonList = applicationConf.getUser();
        Gson gson = new Gson();
        Type type = new TypeToken<AdminUserPojo>() {
        }.getType();
        List<AdminUserPojo> userBos = new ArrayList<>();
        for (String user : userJsonList) {
            logger.info("json user string is ------------------- {}", user);
            AdminUserPojo u = gson.fromJson(user, type);
            logger.info("json user info is ------------------- {}", u.toString());
            userBos.add(u);
        }
        String cryptPassword = DESPlusUtil.Get().encrypt(password);
        for (AdminUserPojo storageUser : userBos) {
            String _userName = storageUser.getU_name();
            String _password = storageUser.getU_password();
            if (_userName.equals(username) && _password.equals(cryptPassword)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public RestApiMsg addJobUser(JobUserBo jobUserBo) {
        logger.info("add job user service, save job user to string {}", jobUserBo.toString());
        RestApiMsg restApiMsg = new RestApiMsg();
        restApiMsg.setMessage("ok");
        // 保存base64位图片
      // String userImageFileName = storageService.storeBase64Image(2, jobUserBo.getJob_user_photo());
        //保存图片名称
     //   jobUserBo.setJob_user_photo(userImageFileName);
        //保存数据
        int jobUserId = jobUserMapper.setJobUser(jobUserBo);
        logger.info("add job user service, save job user db result job user Id {}", jobUserId);
        if (jobUserId != 1) {
            restApiMsg.setMessage("fail");
        }
        return restApiMsg;
    }

    @Override
    public RestApiMsg updateJobUser(JobUserBo jobUserBo) {
        RestApiMsg restApiMsg = new RestApiMsg();
        restApiMsg.setMessage("false");
        int i = jobUserMapper.updateJobUser(jobUserBo);
        if (i == 1) {
            restApiMsg.setMessage("ok");
        }
        return restApiMsg;
    }

    @Override
    public RestApiMsg clearJobUser(String jobUserId) {
        RestApiMsg restApiMsg = new RestApiMsg();
        if (StringUtils.isNotBlank(jobUserId)) {
            Integer converJobUserId = NumberUtils.createInteger(jobUserId);
            JobUserBo jobUserBo = new JobUserBo();
            jobUserBo.setJob_user_id(converJobUserId);
            jobUserBo.setHidden(0);
            restApiMsg = updateJobUser(jobUserBo);
        }
        return restApiMsg;
    }
}
