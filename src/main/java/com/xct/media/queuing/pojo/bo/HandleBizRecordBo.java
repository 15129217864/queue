package com.xct.media.queuing.pojo.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Chris on 2018/4/30.
 */
public class HandleBizRecordBo {

    private int handle_biz_id;
    private int job_user_id;
    private int base_biz_id;
    private int transact_biz_id;
    private String handle_start_date;
    private String handle_end_date;

    private int hidden = 1;

    public int getHandle_biz_id() {
        return handle_biz_id;
    }

    public void setHandle_biz_id(int handle_biz_id) {
        this.handle_biz_id = handle_biz_id;
    }

    public int getJob_user_id() {
        return job_user_id;
    }

    public void setJob_user_id(int job_user_id) {
        this.job_user_id = job_user_id;
    }

    public int getBase_biz_id() {
        return base_biz_id;
    }

    public void setBase_biz_id(int base_biz_id) {
        this.base_biz_id = base_biz_id;
    }

    public int getTransact_biz_id() {
        return transact_biz_id;
    }

    public void setTransact_biz_id(int transact_biz_id) {
        this.transact_biz_id = transact_biz_id;
    }

    public String getHandle_start_date() {
        return handle_start_date;
    }

    public void setHandle_start_date(String handle_start_date) {
        this.handle_start_date = handle_start_date;
    }

    public String getHandle_end_date() {
        return handle_end_date;
    }

    public void setHandle_end_date(String handle_end_date) {
        this.handle_end_date = handle_end_date;
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
