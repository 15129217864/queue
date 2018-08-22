package com.xct.media.queuing.mapper;

import com.xct.media.queuing.pojo.bo.QueueBo;

import java.util.List;

/**
 * Created by Chris on 2018/6/30.
 */
public interface QueueMapper {

    /**
     * 保存队列
     *
     * @param queueBo
     * @return
     */
    int setQueuingUp(QueueBo queueBo);


    /**
     * 查询排队叫号数据
     *
     * @param queueBo
     * @return
     */
    List<QueueBo> queryQueuingUpSet(QueueBo queueBo);

    /**
     * 修改排队叫号数据
     *
     * @param queueBo
     * @return
     */
    int updateQueuingUp(QueueBo queueBo);
}
