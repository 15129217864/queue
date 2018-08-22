package com.xct.media.queuing.service.impl;

import com.xct.media.queuing.pojo.BackgroundPojo;
import com.xct.media.queuing.pojo.SetBizScreenInfoPojo;
import com.xct.media.queuing.pojo.bo.SetScreenInfoBo;
import com.xct.media.queuing.service.BasePushService;
import com.xct.media.queuing.service.CommonService;
import com.xct.media.queuing.service.SetBizScreenService;
import com.xct.media.queuing.service.SetStripedScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Chris on 2018/6/3.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private SetBizScreenService setBizScreenService;

    @Autowired
    private SetStripedScreenService setStripedScreenService;

    @Autowired
    private BasePushService basePushService;

    @Override
    public boolean pushBackground(BackgroundPojo backgroundPojo) {
        int type = backgroundPojo.getType();
        List<String> toIpList = null;
        if (type == 0) {
            // 业务屏数据
            List<SetBizScreenInfoPojo> setBizScreenInfoList = setBizScreenService.queryBizScreenSet();
            // 获取所有业务屏的ip
            toIpList = setBizScreenInfoList.stream()
                    .map(bizScreen -> bizScreen.getBiz_screen_ip())
                    .collect(toList());
        } else {
            List<SetScreenInfoBo> setScreenInfoList = setStripedScreenService.queryStripedScreenSet();
            // 获取所有条屏的业务信息
            toIpList = setScreenInfoList.stream()
                    .map(screen -> screen.getScreen_ip())
                    .collect(toList());
        }
        // 推送背景图
        for (String toIp : toIpList) {
            basePushService.pushBackgroundBase(toIp, backgroundPojo);
        }
        return true;
    }
}
