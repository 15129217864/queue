package com.xct.media.queuing.service.impl;

import com.xct.media.queuing.mapper.BaseBizMapper;
import com.xct.media.queuing.mapper.JobUserMapper;
import com.xct.media.queuing.mapper.SetBizScreenInfoMapper;
import com.xct.media.queuing.pojo.SetBizScreenInfoPojo;
import com.xct.media.queuing.pojo.bo.BaseBizBo;
import com.xct.media.queuing.pojo.bo.JobUserBo;
import com.xct.media.queuing.pojo.bo.SetBizScreenInfoBo;
import com.xct.media.queuing.service.BasePushService;
import com.xct.media.queuing.service.SetBizScreenService;
import com.xct.media.queuing.util.RestApiMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chris on 2018/5/1.
 */
@Service
public class SetBizScreenServiceImpl implements SetBizScreenService {

    @Autowired
    private SetBizScreenInfoMapper setBizScreenInfoMapper;

    @Autowired
    private JobUserMapper jobUserMapper;

    @Autowired
    private BaseBizMapper baseBizMapper;

    @Autowired
    private BasePushService basePushService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String setBizScreen(SetBizScreenInfoBo setBizScreenInfo) {
        String result = "false";
        int bizScreenId = setBizScreenInfoMapper.setSetBizScreenInfo(setBizScreenInfo);
        if (bizScreenId > 0) {
            result = "true";
        }
        return result;
    }

    @Override
    public RestApiMsg modifyBizScreenSetting(SetBizScreenInfoBo setBizScreenInfo) {
        RestApiMsg restApiMsg = new RestApiMsg();
        restApiMsg.setMessage("false");
        int i = setBizScreenInfoMapper.updateBizScreenInfo(setBizScreenInfo);
        if (i == 1) {
            restApiMsg.setMessage("ok");
        }
        return restApiMsg;

    }

    @Override
    public List<SetBizScreenInfoPojo> queryBizScreenSet() {
        List<SetBizScreenInfoBo> setBizScreenInfoList = setBizScreenInfoMapper.querySetBizScreenInfoSet(new SetBizScreenInfoBo());
        return setBizScreenInfoList.stream().map(bizScreen -> {
            SetBizScreenInfoPojo setBizScreenInfoPojo = new SetBizScreenInfoPojo();
            int jobUserId = bizScreen.getJob_user_id();
            int baseBizId = bizScreen.getBase_biz_id();
            JobUserBo jobUserBo = jobUserMapper.queryByJobUser(jobUserId);
            BaseBizBo baseBizBo = baseBizMapper.queryByBaseBizInfo(baseBizId);
            setBizScreenInfoPojo.setBase_biz_id(baseBizId);
            setBizScreenInfoPojo.setBiz_screen_id(bizScreen.getBiz_screen_id());
            setBizScreenInfoPojo.setBiz_screen_ip(bizScreen.getBiz_screen_ip());
            setBizScreenInfoPojo.setBiz_screen_mark(bizScreen.getBiz_screen_mark());
            setBizScreenInfoPojo.setJob_user_id(jobUserId);
            setBizScreenInfoPojo.setJobUser(jobUserBo);
            setBizScreenInfoPojo.setBaseBiz(baseBizBo);
            return setBizScreenInfoPojo;
        }).collect(Collectors.toList());
    }

    @Override
    public String settingsBizScreen(List<Integer> bizScreenIdList) {
        for (int screenId : bizScreenIdList) {
            SetBizScreenInfoBo setBizScreenInfoBo = new SetBizScreenInfoBo();
            setBizScreenInfoBo.setBiz_screen_id(screenId);
            SetBizScreenInfoBo setBizScreenInfo = setBizScreenInfoMapper.queryBySetBizScreenInfo(setBizScreenInfoBo);
            logger.info("get set biz screen info data {}", setBizScreenInfo.toString());
            int jobUserId = setBizScreenInfo.getJob_user_id();
            int baseBizId = setBizScreenInfo.getBase_biz_id();
            String toIp = setBizScreenInfo.getBiz_screen_ip();
            logger.info("get biz screen toIp {}", toIp);

            JobUserBo jobUserBo = jobUserMapper.queryByJobUser(jobUserId);
            logger.info("get job user data {}", jobUserBo.toString());
            BaseBizBo baseBizBo = baseBizMapper.queryByBaseBizInfo(baseBizId);
            logger.info("get base biz data {}", baseBizBo.toString());
            SetBizScreenInfoPojo setBizScreenInfoPojo = new SetBizScreenInfoPojo();
            setBizScreenInfoPojo.setBase_biz_id(baseBizId);
            setBizScreenInfoPojo.setBiz_screen_id(setBizScreenInfo.getBiz_screen_id());
            setBizScreenInfoPojo.setBiz_screen_ip(toIp);
            setBizScreenInfoPojo.setBiz_screen_mark(setBizScreenInfo.getBiz_screen_mark());
            setBizScreenInfoPojo.setJob_user_id(jobUserId);
            setBizScreenInfoPojo.setJobUser(jobUserBo);
            setBizScreenInfoPojo.setBaseBiz(baseBizBo);
            logger.info("setting biz screen, send message {} ", setBizScreenInfo.toString());
            basePushService.pushQueueBizScreenBase(toIp, setBizScreenInfoPojo);
        }
        return "true";
    }

    @Override
    public RestApiMsg clearBizScreen(String bizScreenId) {
        RestApiMsg restApiMsg = new RestApiMsg();
        if (StringUtils.isNotBlank(bizScreenId)) {
            Integer converBizScreenId = NumberUtils.createInteger(bizScreenId);

            SetBizScreenInfoBo setBizScreenInfo = new SetBizScreenInfoBo();
            setBizScreenInfo.setBiz_screen_id(converBizScreenId);
            setBizScreenInfo.setHidden(0);
            restApiMsg = modifyBizScreenSetting(setBizScreenInfo);
        }
        return restApiMsg;
    }

}
