package com.xct.media.queuing.controller;

import com.xct.media.queuing.pojo.QueueUpPojo;
import com.xct.media.queuing.pojo.bo.QueueBo;
import com.xct.media.queuing.service.QueuingUpService;
import com.xct.media.queuing.util.RestApiMsg;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Chris on 2018/7/1.
 */
@RestController
@RequestMapping("/queue")
public class QueuingUpController {

    private Logger logger = LoggerFactory.getLogger(QueuingUpController.class);

    @Autowired
    private QueuingUpService queuingUpService;


    /**
     * 获取排队号码
     *
     * @param baseBizId
     * @return
     */
    @RequestMapping(value = "/getQueueNum/{baseBizId}", method = RequestMethod.POST)
    public RestApiMsg<String> getQueuingUpNum(@PathVariable int baseBizId) {
        QueueBo queue = new QueueBo();
        queue.setBase_biz_id(baseBizId);
        String queueNum = queuingUpService.getQueuingUpNum(queue);
        RestApiMsg<String> restApiMsg = new RestApiMsg<>();
        if (StringUtils.isNotBlank(queueNum)) {
            logger.info("get service queue num [{}]", queueNum);
            restApiMsg.setRestCode(0);
            restApiMsg.setRestData(queueNum);
        } else {
            restApiMsg.setRestCode(-1);
            restApiMsg.setMessage("未获取到排队号");
            logger.info("get service queue num error.");
        }
        return restApiMsg;
    }

    /**
     * 叫号
     *
     * @param baseBizId
     * @return
     */
    @RequestMapping(value = "/takeQueueNext/{baseBizId}", method = RequestMethod.POST)
    public RestApiMsg<QueueUpPojo> takeQueuingUpNext(@PathVariable int baseBizId) {
        logger.info("take queue next.");
        QueueUpPojo queueBo = queuingUpService.takeQueuingUp(baseBizId);
        RestApiMsg<QueueUpPojo> restApiMsg = new RestApiMsg<>();
        if (queueBo != null) {
            restApiMsg.setRestCode(0);
            restApiMsg.setRestData(queueBo);
        } else {
            restApiMsg.setRestCode(-1);
            restApiMsg.setMessage("获取一个排队号码失败！");
        }
        return restApiMsg;
    }


    /**
     * 排队数据
     *
     * @param baseBizId
     * @return
     */
    @RequestMapping(value = "/takeQueueSize/{baseBizId}", method = RequestMethod.POST)
    public RestApiMsg<QueueUpPojo> takeQueuingUpSize(@PathVariable int baseBizId) {
        logger.info("take queue size.");
        QueueUpPojo queueBo = queuingUpService.takeQueuingSize(baseBizId);
        RestApiMsg<QueueUpPojo> restApiMsg = new RestApiMsg<>();
        if (queueBo != null) {
            restApiMsg.setRestCode(0);
            restApiMsg.setRestData(queueBo);
        } else {
            restApiMsg.setRestCode(-1);
            restApiMsg.setMessage("获取下一个排队号码失败！");
        }
        return restApiMsg;
    }
}
