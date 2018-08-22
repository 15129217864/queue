package com.xct.media.queuing.pojo.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Chris on 2018/4/30.
 */
public class BaseBizBo {


    private int base_biz_id;
    private String base_biz_name;
    private String base_biz_num;

    private int hidden = 1;


    public String getBase_biz_num() {
        return base_biz_num;
    }

    public void setBase_biz_num(String base_biz_num) {
        this.base_biz_num = base_biz_num;
    }

    public int getBase_biz_id() {
        return base_biz_id;
    }

    public void setBase_biz_id(int base_biz_id) {
        this.base_biz_id = base_biz_id;
    }

    public String getBase_biz_name() {
        return base_biz_name;
    }

    public void setBase_biz_name(String base_biz_name) {
        this.base_biz_name = base_biz_name;
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
