package com.xct.media.queuing.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Created by Chris on 2018/7/7.
 */
public class BaseScreenWrapper {

    private List<Integer> bizScreenIdList;

    public List<Integer> getBizScreenIdList() {
        return bizScreenIdList;
    }


    public void setBizScreenIdList(List<Integer> bizScreenIdList) {
        this.bizScreenIdList = bizScreenIdList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
