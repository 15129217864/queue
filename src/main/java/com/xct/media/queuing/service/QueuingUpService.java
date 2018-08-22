package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.QueueUpPojo;
import com.xct.media.queuing.pojo.bo.QueueBo;

/**
 * 排队叫号业务
 * Created by Chris on 2018/6/30.
 */
public interface QueuingUpService {

    String DEFAULT_QUEUE_STATE = "0";

    /**
     * 获取排队叫号序号
     *
     * @param queueBo
     * @return
     */
    String getQueuingUpNum(QueueBo queueBo);


    /**
     * 修改 排队叫号状态
     *
     * @param baseBizId
     */
    QueueUpPojo takeQueuingUp(int baseBizId);

    /**
     * 获取排队数
     *
     * @param baseBizId
     * @return
     */
    QueueUpPojo takeQueuingSize(int baseBizId);


    /**
     * 插队
     * <p>
     * 从列表获取queue 数据，写入插队逻辑
     *
     * @param queueBo
     * @return
     */
    boolean jumpQueuingUp(QueueBo queueBo);

}
