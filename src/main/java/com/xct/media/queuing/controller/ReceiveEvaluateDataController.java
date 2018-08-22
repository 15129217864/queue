package com.xct.media.queuing.controller;

import com.xct.media.queuing.service.EvaluateRecordService;
import com.xct.media.queuing.util.RestApiMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 接收xct评价数据
 *
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 10:02.
 * @Description:
 */
@RestController
@RequestMapping("/queue/evaluate")
public class ReceiveEvaluateDataController {

    @Autowired
    private EvaluateRecordService evaluateRecordService;


    private Logger logger = LoggerFactory.getLogger(ReceiveEvaluateDataController.class);

    /**
     * 评价接口
     *
     * @param transactId
     * @param evaluateVal
     * @return
     */
    @RequestMapping(value = "/receive/{transactId}/{evaluateVal}", method = RequestMethod.POST)
    public RestApiMsg<String> receiveData(@PathVariable int transactId, @PathVariable String evaluateVal) {
        logger.info("evaluate controller transactId [{}], evaluateVal [{}]", transactId, evaluateVal);
        String resultStatus = evaluateRecordService.evaluateRecord(transactId, evaluateVal);
        RestApiMsg<String> restApiMsg = new RestApiMsg<>();
        restApiMsg.setRestCode(0);
        restApiMsg.setMessage(resultStatus);
        return restApiMsg;
    }
}
