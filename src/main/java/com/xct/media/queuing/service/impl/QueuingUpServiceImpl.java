package com.xct.media.queuing.service.impl;

import com.xct.media.queuing.mapper.BaseBizMapper;
import com.xct.media.queuing.mapper.QueueMapper;
import com.xct.media.queuing.pojo.QueueUpPojo;
import com.xct.media.queuing.pojo.bo.BaseBizBo;
import com.xct.media.queuing.pojo.bo.QueueBo;
import com.xct.media.queuing.service.QueuingUpService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chris on 2018/6/30.
 */
@Service
public class QueuingUpServiceImpl implements QueuingUpService {

    @Autowired
    private QueueMapper queueMapper;

    @Autowired
    private BaseBizMapper baseBizMapper;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    private Logger logger = LoggerFactory.getLogger(QueuingUpServiceImpl.class);

    /**
     * 取号业务
     *
     * @param queueBo
     * @return
     */
    @Override
    public String getQueuingUpNum(QueueBo queueBo) {
        String queue_num = "";
        checkedQueue(queueBo);
        List<QueueBo> queryQueuingUpList = queueMapper.queryQueuingUpSet(queueBo);
        String currentDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        logger.info("get queue num, current date {}", currentDate);

        List<QueueBo> queryQueuingUpStreamList = queryQueuingUpList.stream()
                .filter(queue -> {
                    String takeNumTime = queue.getTake_num_time();
                    String filterCurrentDate = StringUtils.substringBefore(currentDate, " ");
                    return StringUtils.containsIgnoreCase(takeNumTime, filterCurrentDate);
                }).collect(Collectors.toList());

        // 获取当天某一个业务的排队数统计
        int currentQueuingUpSize = queryQueuingUpStreamList.size();
        logger.info("get queue num, current date {}", currentDate);
        int nextQueueNum = currentQueuingUpSize + 1;

        logger.info("next queue num [{}]", nextQueueNum);
        // 生成排队号
        queue_num = StringUtils.leftPad(nextQueueNum + "", 3, "0");
        queueBo.setQueue_num(queue_num);
        int baseBizId = queueBo.getBase_biz_id();
        logger.info("get queue bo base biz id {}", baseBizId);
        // 获取基础业务数据
        BaseBizBo baseBizBo = baseBizMapper.queryByBaseBizInfo(baseBizId);
        String baseBizNum = baseBizBo.getBase_biz_num();

        queueBo.setQueue_state(DEFAULT_QUEUE_STATE);
        queueBo.setBase_biz_id(baseBizId);
        queueBo.setTake_num_time(currentDate);

        // 排队落库
        int queueId = queueMapper.setQueuingUp(queueBo);

        // 写入数据库成功写入消息队列
        if (queueId > 0) {
            logger.info("write jms amq key [{}],message value [{}]", baseBizNum, queueBo.toString());
            jmsMessagingTemplate.convertAndSend(baseBizNum, queueBo);
        }

        logger.info("queuing up save data, get queue id is {}", queueId);

        return queue_num;
    }

    /**
     * 叫号业务
     *
     * @param baseBizId
     */
    @Override
    public QueueUpPojo takeQueuingUp(int baseBizId) {
        QueueUpPojo queueUpPojo = new QueueUpPojo();
        String callCurrentDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        logger.info("get queue num, call current date {}", callCurrentDate);
        // 获取基础业务数据
        BaseBizBo baseBizBo = baseBizMapper.queryByBaseBizInfo(baseBizId);
        String baseBizNum = baseBizBo.getBase_biz_num();

        QueueBo queryQueueBo = new QueueBo();
        queryQueueBo.setBase_biz_id(baseBizId);
        queryQueueBo.setQueue_state("0");

        List<QueueBo> queueBoList = queueMapper.queryQueuingUpSet(queryQueueBo);
        int currentQueueNum = queueBoList.size();
        if (currentQueueNum >= 1) {
            QueueBo takeAmqQueueBo = null;
            try {
                // 从amq里拿数据
                takeAmqQueueBo = jmsMessagingTemplate.receiveAndConvert(baseBizNum, QueueBo.class);
            } catch (MessagingException message) {
                logger.error("get amq message is error or null.", message.getMessage());
            }

            if (takeAmqQueueBo != null) {
                // 修改queue_tbl 数据
                takeAmqQueueBo.setQueue_state("1");
                takeAmqQueueBo.setCall_num_time(callCurrentDate);
                // 更新排队叫号状态
                int resultLine = queueMapper.updateQueuingUp(takeAmqQueueBo);
                if (resultLine == 1) {
                    logger.info("take queue num next success.");
                    queueUpPojo.setQueueBo(takeAmqQueueBo);
                    int overage = currentQueueNum - 1;
                    int overageQueue = overage > 0 ? overage : 0;
                    queueUpPojo.setOverageQueue(overageQueue);
                }
            }
        }
        return queueUpPojo;
    }

    @Override
    public QueueUpPojo takeQueuingSize(int baseBizId) {
        QueueUpPojo queueUpPojo = new QueueUpPojo();
        QueueBo queryQueueBo = new QueueBo();
        queryQueueBo.setBase_biz_id(baseBizId);
        queryQueueBo.setQueue_state("0");

        List<QueueBo> queueBoList = queueMapper.queryQueuingUpSet(queryQueueBo);
        int currentQueueNum = queueBoList.size();
        queueUpPojo.setOverageQueue(currentQueueNum);

        return queueUpPojo;
    }

    /**
     * 插队
     *
     * @param queueBo
     * @return
     */
    @Override
    public boolean jumpQueuingUp(QueueBo queueBo) {
        return false;
    }


    private void checkedQueue(QueueBo queueBo) {
        if (queueBo == null)
            throw new RuntimeException("get take queue num is null.");
    }
}
