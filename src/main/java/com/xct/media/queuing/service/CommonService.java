package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.BackgroundPojo;

/**
 * Created by Chris on 2018/6/3.
 */
public interface CommonService {

    /**
     * 设置背景图
     *
     * @param backgroundPojo
     * @return
     */
    boolean pushBackground(BackgroundPojo backgroundPojo);
}
