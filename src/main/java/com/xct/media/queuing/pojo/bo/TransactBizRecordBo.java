package com.xct.media.queuing.pojo.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by Chris on 2018/4/30.
 */
public class TransactBizRecordBo implements Serializable {


    private int transact_biz_id;
    private String id_card;
    private String use_name;
    // 0-男、1-女
    private String use_sex;
    // 用户身份证头像
    private String use_photo;
    private String use_birty;
    private String use_addr;
    // 屏编号
    private String screen_num;
    // 业务名称
    private String biz_name;
    // 排队号码
    private String queue_num;
    // 用户取号抓拍头像
    private String use_current_photo;
    // 叫号当前时间
    private String transact_biz_date;

    private int hidden = 1;

    public int getTransact_biz_id() {
        return transact_biz_id;
    }

    public void setTransact_biz_id(int transact_biz_id) {
        this.transact_biz_id = transact_biz_id;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getUse_name() {
        return use_name;
    }

    public void setUse_name(String use_name) {
        this.use_name = use_name;
    }

    public String getUse_sex() {
        return use_sex;
    }

    public void setUse_sex(String use_sex) {
        this.use_sex = use_sex;
    }

    public String getUse_photo() {
        return use_photo;
    }

    public void setUse_photo(String use_photo) {
        this.use_photo = use_photo;
    }

    public String getUse_birty() {
        return use_birty;
    }

    public void setUse_birty(String use_birty) {
        this.use_birty = use_birty;
    }

    public String getUse_addr() {
        return use_addr;
    }

    public void setUse_addr(String use_addr) {
        this.use_addr = use_addr;
    }

    public String getScreen_num() {
        return screen_num;
    }

    public void setScreen_num(String screen_num) {
        this.screen_num = screen_num;
    }

    public String getBiz_name() {
        return biz_name;
    }

    public void setBiz_name(String biz_name) {
        this.biz_name = biz_name;
    }

    public String getQueue_num() {
        return queue_num;
    }

    public void setQueue_num(String queue_num) {
        this.queue_num = queue_num;
    }

    public String getUse_current_photo() {
        return use_current_photo;
    }

    public void setUse_current_photo(String use_current_photo) {
        this.use_current_photo = use_current_photo;
    }

    public String getTransact_biz_date() {
        return transact_biz_date;
    }

    public void setTransact_biz_date(String transact_biz_date) {
        this.transact_biz_date = transact_biz_date;
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
