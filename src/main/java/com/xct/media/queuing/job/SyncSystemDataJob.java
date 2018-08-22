package com.xct.media.queuing.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Chris on 2018/4/24.
 */
@Component
public class SyncSystemDataJob {


    /**
     * 同步办理完业务之后的数据
     * <p>
     * 每天早8点到晚8点每5分20秒执行一次同步数据
     */
    @Scheduled(cron = "20 0/5 8-20 * * *")
    public void sysCourtBizData() {

    }

}
