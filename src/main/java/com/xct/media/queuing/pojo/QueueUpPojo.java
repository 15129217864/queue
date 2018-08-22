package com.xct.media.queuing.pojo;

import com.xct.media.queuing.pojo.bo.QueueBo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Chris on 2018/7/21.
 */
public class QueueUpPojo {

    private int overageQueue = 0;

    private QueueBo queueBo;

    public int getOverageQueue() {
        return overageQueue;
    }

    public void setOverageQueue(int overageQueue) {
        this.overageQueue = overageQueue;
    }

    public QueueBo getQueueBo() {
        return queueBo;
    }

    public void setQueueBo(QueueBo queueBo) {
        this.queueBo = queueBo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
