package com.xct.media.queuing.exceptions;

/**
 * Created by Chris on 2018/7/1.
 */
public class QueueException extends RuntimeException {

    private String queueExce = "queue exception";

    public QueueException(String message) {
        super(message);
    }

    public String getQueueExce() {
        return queueExce;
    }

    public void setQueueExce(String queueExce) {
        this.queueExce = queueExce;
    }
}
