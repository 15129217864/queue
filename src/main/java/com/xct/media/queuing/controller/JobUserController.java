package com.xct.media.queuing.controller;

import com.xct.media.queuing.pojo.bo.JobUserBo;
import com.xct.media.queuing.service.JobUserService;
import com.xct.media.queuing.storage.StorageService;
import com.xct.media.queuing.util.RestApiMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/5/2 11:56.
 * @Description:
 */
@RestController
@RequestMapping("/queue/user")
public class JobUserController {

    @Autowired
    private JobUserService jobUserService;
    @Autowired
    private StorageService storageService;

    private Logger logger = LoggerFactory.getLogger(JobUserController.class);
    /**
     * 获取所有人员信息
     *
     * @return 返回人员信息json字符串
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<JobUserBo> getAllUserInfo() {
        return jobUserService.queryAllJobUserInfo();
    }

    /**
     * 获取所有人员信息 供下拉框选择使用
     *
     * @return
     */
    @RequestMapping(value = "/getBaseUserInfo", method = RequestMethod.GET)
    public List<JobUserBo> getUserInfo() {
        return jobUserService.selectBaseJobUserInfo();
    }

    /**
     * 保存人员基本信息
     *
     * @param jobUserBo
     * @return
     */
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
    public RestApiMsg addBaseJobUserInfo(JobUserBo jobUserBo, @RequestParam("file") MultipartFile file) {
        logger.info("controller  addBaseJobUserInfo ",jobUserBo);
      String job_user_photo =storageService.store("2",file);
      jobUserBo.setJob_user_photo(job_user_photo);
       return jobUserService.addJobUser(jobUserBo);
    }

    /**
     * 修改人员基本信息
     *
     * @param jobUserBo
     * @return
     */
    @RequestMapping(value = "/updateUserJob", method = RequestMethod.POST)
    public RestApiMsg updateUserInfo(JobUserBo jobUserBo,@RequestParam("file") MultipartFile file) {
        logger.info("controller  updateUserInfo {} ",jobUserBo);
        String job_user_photo = storageService.store("2",file);
        jobUserBo.setJob_user_photo(job_user_photo);
        return jobUserService.updateJobUser(jobUserBo);
    }

    /**
     * 删除人员基本信息
     *
     * @param jobUserId
     * @return
     */
    @RequestMapping(value = "/clear")
    public RestApiMsg clearJobUser(@RequestParam("id") String jobUserId) {
        return jobUserService.clearJobUser(jobUserId);
    }
}
