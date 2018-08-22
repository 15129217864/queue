package com.xct.media.queuing.pojo.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by Chris on 2018/4/30.
 */
public class JobUserBo implements Serializable {

    private int job_user_id;
    private String job_user_name;

    //0-男，1-女
    private String job_user_sex;
    private String job_user_num;
    private String job_user_photo;

    private int hidden = 1;


    public int getJob_user_id() {
        return job_user_id;
    }

    public void setJob_user_id(int job_user_id) {
        this.job_user_id = job_user_id;
    }

    public String getJob_user_name() {
        return job_user_name;
    }

    public void setJob_user_name(String job_user_name) {
        this.job_user_name = job_user_name;
    }

    public String getJob_user_sex() {
        return job_user_sex;
    }

    public void setJob_user_sex(String job_user_sex) {
        this.job_user_sex = job_user_sex;
    }

    public String getJob_user_num() {
        return job_user_num;
    }

    public void setJob_user_num(String job_user_num) {
        this.job_user_num = job_user_num;
    }

    public String getJob_user_photo() {
        return job_user_photo;
    }

    public void setJob_user_photo(String job_user_photo) {
        this.job_user_photo = job_user_photo;
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
