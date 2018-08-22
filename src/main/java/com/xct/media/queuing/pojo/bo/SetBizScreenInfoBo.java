package com.xct.media.queuing.pojo.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by Chris on 2018/4/30.
 */
public class SetBizScreenInfoBo implements Serializable {

    private int biz_screen_id;
    private int base_biz_id;
    private int job_user_id;
    //0-办理业务、1-业务员
    private String biz_screen_mark;
    private String biz_screen_ip;
    //绑定的业务名称
    private String base_name;
    //绑定的业务员姓名
    private String user_name;

    private int hidden = 1;


    public String getBase_name() {
        return base_name;
    }

    public void setBase_name(String base_name) {
        this.base_name = base_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getBiz_screen_id() {
        return biz_screen_id;
    }

    public void setBiz_screen_id(int biz_screen_id) {
        this.biz_screen_id = biz_screen_id;
    }

    public int getBase_biz_id() {
        return base_biz_id;
    }

    public void setBase_biz_id(int base_biz_id) {
        this.base_biz_id = base_biz_id;
    }

    public int getJob_user_id() {
        return job_user_id;
    }

    public void setJob_user_id(int job_user_id) {
        this.job_user_id = job_user_id;
    }

    public String getBiz_screen_mark() {
        return biz_screen_mark;
    }

    public void setBiz_screen_mark(String biz_screen_mark) {
        this.biz_screen_mark = biz_screen_mark;
    }

    public String getBiz_screen_ip() {
        return biz_screen_ip;
    }

    public void setBiz_screen_ip(String biz_screen_ip) {
        this.biz_screen_ip = biz_screen_ip;
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
