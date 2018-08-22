package com.xct.media.queuing.service.impl;

import com.google.gson.Gson;
import com.xct.media.queuing.conf.ApplicationConf;
import com.xct.media.queuing.pojo.BackgroundPojo;
import com.xct.media.queuing.pojo.SetBizScreenInfoPojo;
import com.xct.media.queuing.pojo.bo.SetScreenInfoBo;
import com.xct.media.queuing.pojo.bo.TransactBizRecordBo;
import com.xct.media.queuing.service.BasePushService;
import com.xct.media.queuing.util.DatagramPackaging;
import com.xct.media.queuing.util.SendMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Chris on 2018/4/30.
 */
@Service
public class BasePushServiceImpl implements BasePushService {


    private Logger logger = LoggerFactory.getLogger(BasePushServiceImpl.class);

    @Autowired
    private ApplicationConf applicationConf;

    /**
     * 异步推送
     *
     * @param toIp              推送到达地址
     * @param datagramMark      报文类型标记
     * @param transactBizRecord 叫号业务数据
     */
    @Async
    @Override
    public void pushQueueCallOutBiz(String toIp, String datagramMark, TransactBizRecordBo transactBizRecord) {
        logger.info("push screen biz--------start");
        // 封装报文数据
        baseQueuePackaging(toIp, datagramMark, transactBizRecord, CALL_OUT);
        logger.info("push screen biz--------end");
    }

    /**
     * 异步推送
     *
     * @param toIp
     * @param datagramMark
     * @param transactBizRecord
     */
    @Async
    @Override
    public void pushQueueStripedScreenBiz(String toIp, String datagramMark, TransactBizRecordBo transactBizRecord) {
        logger.info("push striped screen biz--------start");
        baseQueuePackaging(toIp, datagramMark, transactBizRecord, STRIPED_SCREEN_BIZ);
        logger.info("push striped screen biz--------end");
    }

    @Override
    public void pushQueueStripedScreenBase(String toIp, SetScreenInfoBo setScreenInfo) {
        // 封装报文数据
        DatagramPackaging<SetScreenInfoBo> datagramPackaging = new DatagramPackaging<>();
        datagramPackaging.setDatagram(setScreenInfo);
        datagramPackaging.setIpAndPort(applicationConf.getService());

        // 转换成json
        Gson gson = new Gson();
        String datagramJson = gson.toJson(datagramPackaging);
        logger.info("push set striped screen json datagram {} ", datagramJson);
        // push udp datagram
        SendMessageUtils.sendUdpDatagramPacket(toIp, STRIPED_SCREEN_BASE, datagramJson, SEND_PORT);
    }

    @Async
    @Override
    public void pushQueueBizScreenBase(String toIp, SetBizScreenInfoPojo setBizScreenInfo) {

        // 封装报文数据
        DatagramPackaging<SetBizScreenInfoPojo> datagramPackaging = new DatagramPackaging<>();
        datagramPackaging.setDatagram(setBizScreenInfo);
        datagramPackaging.setIpAndPort(applicationConf.getService());

        // 转换成json
        Gson gson = new Gson();
        String datagramJson = gson.toJson(datagramPackaging);
        logger.info("push set biz screen pojo json datagram {} ", datagramJson);
        // push udp datagram
        SendMessageUtils.sendUdpDatagramPacket(toIp, BIZ_SCREEN, datagramJson, SEND_PORT);
    }

    @Override
    public void pushBackgroundBase(String toIp, BackgroundPojo background) {
        // 封装报文数据
        DatagramPackaging<BackgroundPojo> datagramPackaging = new DatagramPackaging<>();
        datagramPackaging.setDatagram(background);
        datagramPackaging.setIpAndPort(applicationConf.getService());

        // 转换成json
        Gson gson = new Gson();
        String datagramJson = gson.toJson(datagramPackaging);
        logger.info("push set striped screen json datagram {} ", datagramJson);
        // push udp datagram
        SendMessageUtils.sendUdpDatagramPacket(toIp, BG_COMMAND, datagramJson, SEND_PORT);
    }


    /**
     * 基础排队push封装
     *
     * @param toIp
     * @param datagramMark
     * @param transactBizRecord
     * @param command
     */
    private void baseQueuePackaging(String toIp, String datagramMark,
                                    TransactBizRecordBo transactBizRecord,
                                    String command) {

        // 封装报文数据
        DatagramPackaging<TransactBizRecordBo> datagramPackaging = new DatagramPackaging<>();
        datagramPackaging.setCategory(datagramMark);
        datagramPackaging.setDatagram(transactBizRecord);
        datagramPackaging.setIpAndPort(applicationConf.getService());

        // 转换成json
        Gson gson = new Gson();
        String datagramJson = gson.toJson(datagramPackaging);
        logger.info("push json datagram {} ", datagramJson);
        // push udp datagram
        SendMessageUtils.sendUdpDatagramPacket(toIp, command, datagramJson, SEND_PORT);
    }
}
