package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.CourtTakeNumPojo;
import com.xct.media.queuing.util.RestApiMsg;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Chris on 2018/4/30.
 */
public interface ReceiveThirdDataService {

    /**
     * 解析第三数据,每叫号业务推送
     *
     * @param courtTakeNumPojo
     * @return
     */
    RestApiMsg<String> transferThirdData(CourtTakeNumPojo courtTakeNumPojo, HttpServletRequest request);
}
