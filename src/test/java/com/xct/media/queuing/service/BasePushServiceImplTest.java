package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.BackgroundPojo;
import com.xct.media.queuing.pojo.SetBizScreenInfoPojo;
import com.xct.media.queuing.pojo.bo.BaseBizBo;
import com.xct.media.queuing.pojo.bo.JobUserBo;
import com.xct.media.queuing.pojo.bo.SetScreenInfoBo;
import com.xct.media.queuing.pojo.bo.TransactBizRecordBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Chris on 2018/5/12.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasePushServiceImplTest {

    @Autowired
    private BasePushService basePushService;


    @Test
    public void pushQueueBizScreenBaseTest() {
        SetBizScreenInfoPojo setBizScreenInfo = new SetBizScreenInfoPojo();
        BaseBizBo baseBizBo = new BaseBizBo();
        JobUserBo jobUser = new JobUserBo();

        baseBizBo.setBase_biz_id(13);
        baseBizBo.setBase_biz_name("网上、跨域立案");
        baseBizBo.setBase_biz_num("A002");
        setBizScreenInfo.setBaseBiz(baseBizBo);


        jobUser.setJob_user_id(1);
        jobUser.setJob_user_name("chris");
        jobUser.setJob_user_num("NK00089898");
        jobUser.setJob_user_photo("c591c317f41b5db1183fb486b3791530.png");
        jobUser.setJob_user_sex("0");

        setBizScreenInfo.setJobUser(jobUser);

        setBizScreenInfo.setBiz_screen_mark("1");
        setBizScreenInfo.setBiz_screen_id(1);
        setBizScreenInfo.setBiz_screen_ip("192.168.10.128");

        basePushService.pushQueueBizScreenBase("192.168.10.128", setBizScreenInfo);
    }

    /**
     * 排队叫号条屏基础数据推送
     */
    @Test
    public void pushQueueStripedScreenBase() {
        SetScreenInfoBo setScreenInfoBo = new SetScreenInfoBo();
        setScreenInfoBo.setScreen_id(1);
        setScreenInfoBo.setBase_biz_id(13);
        setScreenInfoBo.setBase_biz("网上、跨域立案");
        setScreenInfoBo.setScreen_mark("0");
        setScreenInfoBo.setScreen_type("2");
        setScreenInfoBo.setScreen_num("A009");
        setScreenInfoBo.setScreen_ip("192.168.10.183");
        basePushService.pushQueueStripedScreenBase("192.168.10.183", setScreenInfoBo);
    }

    /**
     * 排队叫号条屏业务数据推送
     */
    @Test
    public void pushQueueStripedScreenBiz() {
        TransactBizRecordBo transactBizRecord = new TransactBizRecordBo();
        transactBizRecord.setTransact_biz_id(22);
        transactBizRecord.setId_card("234234324198822222222");
        transactBizRecord.setUse_name("chris");
        transactBizRecord.setUse_sex("0");
        transactBizRecord.setUse_photo("6NefU0XSWTZIyRH7zrAK201805121621.png");
        transactBizRecord.setUse_birty("1900年12月31日");
        transactBizRecord.setUse_addr("shanghai");
        transactBizRecord.setScreen_num("81");
        transactBizRecord.setBiz_name("网上、跨域立案");
        transactBizRecord.setQueue_num("E018");
        transactBizRecord.setUse_current_photo("6NefU0XSWTZIyRH7zrAK201805121621.png");
        basePushService.pushQueueStripedScreenBiz("192.168.10.163", "1/0", transactBizRecord);
    }

    /**
     * 背景图推送
     */
    @Test
    public void pushBackground() {
        BackgroundPojo bg = new BackgroundPojo();
        bg.setType(1);
        bg.setBg_fileName("123456.png");
        basePushService.pushBackgroundBase("192.168.10.183", bg);
    }
}
