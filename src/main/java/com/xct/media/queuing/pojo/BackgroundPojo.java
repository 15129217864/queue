package com.xct.media.queuing.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Chris on 2018/5/19.
 */
public class BackgroundPojo {

    // 0-业务背景图；1-横条屏背景图
    private int type;

    // 背景图名字；存储路径"/queue/3"
    private String bg_fileName;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBg_fileName() {
        return bg_fileName;
    }

    public void setBg_fileName(String bg_fileName) {
        this.bg_fileName = bg_fileName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
