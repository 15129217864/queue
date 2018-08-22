package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.bo.SetScreenInfoBo;
import com.xct.media.queuing.util.RestApiMsg;

import java.util.List;

/**
 * Created by Chris on 2018/5/1.
 */
public interface SetStripedScreenService {

    /**
     * 保存横条屏设置
     *
     * @param setScreenInfo
     * @return
     */
    String setStripedScreen(SetScreenInfoBo setScreenInfo);

    /**
     * 修改横条平设置
     *
     * @param setScreenInfo
     * @return
     */
    RestApiMsg modifyStripedScreenSetting(SetScreenInfoBo setScreenInfo);


    /**
     * 查询横条数据
     *
     * @return
     */
    List<SetScreenInfoBo> queryStripedScreenSet();


    /**
     * 设置striped Screen
     *
     * @param screenIdSet
     * @return
     */
    String settingsStripedScreen(List<Integer> screenIdSet);

    /**
     * 删除横条屏数据
     *
     * @param screenId
     * @return
     */
    RestApiMsg clearStripedScreen(String screenId);
}
