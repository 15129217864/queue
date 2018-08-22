package com.xct.media.queuing.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 接收触发评价pojo
 * <p>
 * Created by Chris on 2018/4/27.
 */
public class ReceiveTouchEvaluatePojo implements Serializable {

    private String bizNum;
    private String bizName;
    private String queueNum;
    private String touchTime;

    public String getBizNum() {
        return bizNum;
    }

    public void setBizNum(String bizNum) {
        this.bizNum = bizNum;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getQueueNum() {
        return queueNum;
    }

    public void setQueueNum(String queueNum) {
        this.queueNum = queueNum;
    }

    public String getTouchTime() {
        return touchTime;
    }

    public void setTouchTime(String touchTime) {
        this.touchTime = touchTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
