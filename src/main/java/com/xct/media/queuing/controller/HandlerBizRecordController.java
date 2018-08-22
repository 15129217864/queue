package com.xct.media.queuing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yuchao.
 * @Date: Create in 2018/4/27 21:50.
 * @Description: 业务处理记录
 */
@Controller
@RequestMapping("/queue/handler")
public class HandlerBizRecordController {


    private Logger logger = LoggerFactory.getLogger(HandlerBizRecordController.class);

    @RequestMapping("/dashboard")
    public String queryDashboard() {
        logger.info("query biz dashboard");
        return "dashboard";
    }

}
