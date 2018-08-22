package com.xct.media.queuing.pojo.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评价
 * Created by Chris on 2018/4/30.
 */
public class EvaluateBo {

    private int evaluate_id;

    private int handle_biz_id;
    // 评价值 1好/2中/3差
    private String evaluate_type = "1";
    // 评价状态0-默认状态
    private String evaluate_status = "0";

    private String evaluate_date;

    private int hidden = 1;


    public int getEvaluate_id() {
        return evaluate_id;
    }

    public void setEvaluate_id(int evaluate_id) {
        this.evaluate_id = evaluate_id;
    }

    public int getHandle_biz_id() {
        return handle_biz_id;
    }

    public void setHandle_biz_id(int handle_biz_id) {
        this.handle_biz_id = handle_biz_id;
    }

    public String getEvaluate_type() {
        return evaluate_type;
    }

    public void setEvaluate_type(String evaluate_type) {
        this.evaluate_type = evaluate_type;
    }

    public String getEvaluate_status() {
        return evaluate_status;
    }

    public void setEvaluate_status(String evaluate_status) {
        this.evaluate_status = evaluate_status;
    }

    public String getEvaluate_date() {
        return evaluate_date;
    }

    public void setEvaluate_date(String evaluate_date) {
        this.evaluate_date = evaluate_date;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
