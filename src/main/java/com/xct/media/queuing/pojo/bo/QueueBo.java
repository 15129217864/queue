package com.xct.media.queuing.pojo.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


/**
 * Created by Chris on 2018/6/30.
 */
public class QueueBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int queue_id;
    private int base_biz_id;
    // 排队叫号编号
    private String queue_num;
    /**
     * 0-取号； default
     * 1-叫号；
     * 2-过号；
     * 3-插队
     */
    private String queue_state;
    private String take_num_pic;
    private String take_num_time;
    private String call_num_time;


    public int getQueue_id() {
        return queue_id;
    }

    public void setQueue_id(int queue_id) {
        this.queue_id = queue_id;
    }

    public int getBase_biz_id() {
        return base_biz_id;
    }

    public void setBase_biz_id(int base_biz_id) {
        this.base_biz_id = base_biz_id;
    }

    public String getQueue_num() {
        return queue_num;
    }

    public void setQueue_num(String queue_num) {
        this.queue_num = queue_num;
    }

    public String getQueue_state() {
        return queue_state;
    }

    public void setQueue_state(String queue_state) {
        this.queue_state = queue_state;
    }

    public String getTake_num_pic() {
        return take_num_pic;
    }

    public void setTake_num_pic(String take_num_pic) {
        this.take_num_pic = take_num_pic;
    }

    public String getTake_num_time() {
        return take_num_time;
    }

    public void setTake_num_time(String take_num_time) {
        this.take_num_time = take_num_time;
    }

    public String getCall_num_time() {
        return call_num_time;
    }

    public void setCall_num_time(String call_num_time) {
        this.call_num_time = call_num_time;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
