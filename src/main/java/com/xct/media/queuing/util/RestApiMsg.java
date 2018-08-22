package com.xct.media.queuing.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 20:56.
 * @Description:
 */
public class RestApiMsg<T> {

    // 状态码
    private int restCode;
    //消息状态信息
    private String message;
    //返回数据
    private T restData;

    public int getRestCode() {
        return restCode;
    }

    public void setRestCode(int restCode) {
        this.restCode = restCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRestData() {
        return restData;
    }

    public void setRestData(T restData) {
        this.restData = restData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
