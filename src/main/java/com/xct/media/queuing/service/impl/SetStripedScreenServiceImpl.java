package com.xct.media.queuing.service.impl;

import com.xct.media.queuing.mapper.BaseBizMapper;
import com.xct.media.queuing.mapper.SetScreenInfoMapper;
import com.xct.media.queuing.pojo.bo.BaseBizBo;
import com.xct.media.queuing.pojo.bo.SetScreenInfoBo;
import com.xct.media.queuing.service.BasePushService;
import com.xct.media.queuing.service.SetStripedScreenService;
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
public class SetStripedScreenServiceImpl implements SetStripedScreenService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SetScreenInfoMapper setScreenInfoMapper;

    @Autowired
    private BaseBizMapper baseBizMapper;

    @Autowired
    private BasePushService basePushService;

    @Override
    public String setStripedScreen(SetScreenInfoBo setScreenInfo) {
        String result = "false";
        int saveIncrementId = setScreenInfoMapper.setSetScreenInfo(setScreenInfo);
        if (saveIncrementId > 0) {
            result = "true";
        }
        return result;
    }

    @Override
    public RestApiMsg modifyStripedScreenSetting(SetScreenInfoBo setScreenInfo) {
        RestApiMsg restApiMsg = new RestApiMsg();
        restApiMsg.setMessage("false");
        int resultLine = setScreenInfoMapper.updateSetScreenInfo(setScreenInfo);
        if (resultLine == 1) {
            restApiMsg.setMessage("ok");
        }
        return restApiMsg;
    }

    @Override
    public List<SetScreenInfoBo> queryStripedScreenSet() {
        List<SetScreenInfoBo> stripedScreenInfoList = setScreenInfoMapper.querySetScreenInfoSet(new SetScreenInfoBo());
        return stripedScreenInfoList.stream().map(striped -> {
                    int baseBizId = striped.getBase_biz_id();
                    BaseBizBo baseBiz = baseBizMapper.queryByBaseBizInfo(baseBizId);
                    striped.setBase_biz(baseBiz.getBase_biz_name());
                    return striped;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public String settingsStripedScreen(List<Integer> screenIdSet) {
        for (int screenId : screenIdSet) {
            SetScreenInfoBo setScreenInfoBo = new SetScreenInfoBo();
            setScreenInfoBo.setScreen_id(screenId);
            // 查询很条屏信息
            SetScreenInfoBo querySetScreenInfo = setScreenInfoMapper.queryBySetScreenInfo(setScreenInfoBo);
            logger.info("get setting screen info data {}", querySetScreenInfo.toString());
            String toIp = querySetScreenInfo.getScreen_ip();
            int baseBizId = querySetScreenInfo.getBase_biz_id();
            // 获取对应业务信息
            BaseBizBo baseBiz = baseBizMapper.queryByBaseBizInfo(baseBizId);
            logger.info("get base biz data {}", baseBiz.toString());
            querySetScreenInfo.setBase_biz(baseBiz.getBase_biz_name());
            // 推送数据
            basePushService.pushQueueStripedScreenBase(toIp, querySetScreenInfo);
        }
        return "true";
    }

    @Override
    public RestApiMsg clearStripedScreen(String screenId) {
        RestApiMsg restApiMsg = new RestApiMsg();
        if (StringUtils.isNotBlank(screenId)) {
            Integer coverScreenId = NumberUtils.createInteger(screenId);
            SetScreenInfoBo setScreenInfo = new SetScreenInfoBo();
            setScreenInfo.setScreen_id(coverScreenId);
            setScreenInfo.setHidden(0);
            restApiMsg = modifyStripedScreenSetting(setScreenInfo);
        }

        return restApiMsg;
    }
}
