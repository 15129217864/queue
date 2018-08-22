package com.xct.media.queuing.controller;

import com.xct.media.queuing.pojo.CourtTakeNumPojo;
import com.xct.media.queuing.pojo.ReceiveTouchEvaluatePojo;
import com.xct.media.queuing.service.ReceiveThirdDataService;
import com.xct.media.queuing.util.RestApiMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 接收第三方数据controller
 * Created by Chris on 2018/4/21.
 */
@RestController
@RequestMapping("/queue/court")
public class ReceiveThirdDataController {

    private Logger logger = LoggerFactory.getLogger(ReceiveThirdDataController.class);

    @Autowired
    private ReceiveThirdDataService receiveThirdDataService;

    /**
     * 接收第三方数据controller
     *
     * @param person
     * @return
     */
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    public RestApiMsg<String> receiveData(@RequestBody CourtTakeNumPojo person, HttpServletRequest request) {
        logger.info("get third data : {}", person.toString());
        return receiveThirdDataService.transferThirdData(person, request);
    }

    /**
     * 触发评价业务，接收第三方反馈数据
     *
     * @param evaluate
     * @return
     */
    @RequestMapping(value = "/evaluate", method = RequestMethod.POST)
    public String receiveTouchEvaluateData(@RequestBody ReceiveTouchEvaluatePojo evaluate) {
        logger.info("get touch off evaluate data : {}", evaluate.toString());

        return "true";
    }
}
