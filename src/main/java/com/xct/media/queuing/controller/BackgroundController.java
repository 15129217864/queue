package com.xct.media.queuing.controller;

import com.xct.media.queuing.pojo.BackgroundPojo;
import com.xct.media.queuing.service.CommonService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Chris on 2018/6/3.
 */
@Controller
@RequestMapping("/common")
public class BackgroundController {

    private Logger logger = LoggerFactory.getLogger(BackgroundController.class);

    @Autowired
    private CommonService commonService;

    @RequestMapping("/background")
    @ResponseBody
    public Boolean settingsBackGround(String fileName, String type) {
        logger.info("request param data {}---{}", fileName, type);
        BackgroundPojo backgroundPojo = new BackgroundPojo();
        backgroundPojo.setBg_fileName(fileName);
        backgroundPojo.setType(NumberUtils.createInteger(type));
        return commonService.pushBackground(backgroundPojo);
    }
}
