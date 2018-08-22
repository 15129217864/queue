package com.xct.media.queuing.pojo.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by Chris on 2018/4/30.
 */
public class SetScreenInfoBo implements Serializable {

    private int screen_id;
    private int base_biz_id;
    private String base_biz;

    //0-单屏显示、1-双屏显示
    private String screen_mark;

    //0-左边显示、1-右边显示、2-中间显示
    private String screen_type;
    private String screen_num;
    private String screen_ip;

    private int hidden = 1;

    public int getScreen_id() {
        return screen_id;
    }

    public void setScreen_id(int screen_id) {
        this.screen_id = screen_id;
    }

    public int getBase_biz_id() {
        return base_biz_id;
    }

    public void setBase_biz_id(int base_biz_id) {
        this.base_biz_id = base_biz_id;
    }

    public String getBase_biz() {
        return base_biz;
    }

    public void setBase_biz(String base_biz) {
        this.base_biz = base_biz;
    }

    public String getScreen_mark() {
        return screen_mark;
    }

    public void setScreen_mark(String screen_mark) {
        this.screen_mark = screen_mark;
    }

    public String getScreen_type() {
        return screen_type;
    }

    public void setScreen_type(String screen_type) {
        this.screen_type = screen_type;
    }

    public String getScreen_num() {
        return screen_num;
    }

    public void setScreen_num(String screen_num) {
        this.screen_num = screen_num;
    }

    public String getScreen_ip() {
        return screen_ip;
    }

    public void setScreen_ip(String screen_ip) {
        this.screen_ip = screen_ip;
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
