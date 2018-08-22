package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.BackgroundPojo;
import com.xct.media.queuing.pojo.SetBizScreenInfoPojo;
import com.xct.media.queuing.pojo.bo.SetScreenInfoBo;
import com.xct.media.queuing.pojo.bo.TransactBizRecordBo;

/**
 * 基础推送业务
 * <p>
 * Created by Chris on 2018/4/30.
 */
public interface BasePushService {

    // udp 端口
    int SEND_PORT = 10001;

    // 叫号信息推送
    String CALL_OUT = "CALL_OUT#";

    // 基础信息
    String STRIPED_SCREEN_BASE = "STRIPED_SCREEN_BASE#";

    // 条屏业务指令
    String STRIPED_SCREEN_BIZ = "STRIPED_SCREEN_BIZ#";

    // 基础业务屏数据推送
    String BIZ_SCREEN = "BIZ_SCREEN#";

    // 背景图推送 "bg-background"
    String BG_COMMAND = "bg_command#";


    /**
     * 推送排队叫号业务数据
     *
     * @param toIp              推送到达地址
     * @param datagramMark      报文类型标记
     * @param transactBizRecord 叫号业务数据
     */
    void pushQueueCallOutBiz(String toIp, String datagramMark, TransactBizRecordBo transactBizRecord);


    /**
     * 条屏业务数据推送
     *
     * @param toIp
     * @param datagramMark
     * @param transactBizRecord
     */
    void pushQueueStripedScreenBiz(String toIp, String datagramMark, TransactBizRecordBo transactBizRecord);


    /**
     * 条屏基础数据推送
     *
     * @param toIP
     * @param setScreenInfo
     */
    void pushQueueStripedScreenBase(String toIP, SetScreenInfoBo setScreenInfo);


    /**
     * 业务屏数据推送
     *
     * @param toIp
     * @param setBizScreenInfo
     */
    void pushQueueBizScreenBase(String toIp, SetBizScreenInfoPojo setBizScreenInfo);


    /**
     * 背景图推送
     *
     * @param toIp
     * @param background
     */
    void pushBackgroundBase(String toIp, BackgroundPojo background);

}
