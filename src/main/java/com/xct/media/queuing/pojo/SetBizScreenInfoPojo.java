package com.xct.media.queuing.pojo;

import com.xct.media.queuing.pojo.bo.BaseBizBo;
import com.xct.media.queuing.pojo.bo.JobUserBo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by Chris on 2018/5/1.
 */
public class SetBizScreenInfoPojo implements Serializable {

    private int biz_screen_id;
    private int base_biz_id;
    private int job_user_id;
    private String biz_screen_mark;
    private String biz_screen_ip;

    private JobUserBo jobUser;

    private BaseBizBo baseBiz;

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

    public JobUserBo getJobUser() {
        return jobUser;
    }

    public void setJobUser(JobUserBo jobUser) {
        this.jobUser = jobUser;
    }

    public BaseBizBo getBaseBiz() {
        return baseBiz;
    }

    public void setBaseBiz(BaseBizBo baseBiz) {
        this.baseBiz = baseBiz;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
