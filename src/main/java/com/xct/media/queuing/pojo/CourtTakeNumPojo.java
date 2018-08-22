package com.xct.media.queuing.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Chris on 2018/4/21.
 */
public class CourtTakeNumPojo {

    private String windowNum;
    private String windowBiz;
    private String currentQueueNum;

    private PersonPojo person;


    public String getWindowNum() {
        return windowNum;
    }

    public void setWindowNum(String windowNum) {
        this.windowNum = windowNum;
    }

    public String getWindowBiz() {
        return windowBiz;
    }

    public void setWindowBiz(String windowBiz) {
        this.windowBiz = windowBiz;
    }

    public String getCurrentQueueNum() {
        return currentQueueNum;
    }

    public void setCurrentQueueNum(String currentQueueNum) {
        this.currentQueueNum = currentQueueNum;
    }

    public PersonPojo getPerson() {
        return person;
    }

    public void setPerson(PersonPojo person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
