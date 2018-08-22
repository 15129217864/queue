package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.SetBizScreenInfoPojo;
import com.xct.media.queuing.pojo.bo.SetBizScreenInfoBo;
import com.xct.media.queuing.util.RestApiMsg;

import java.util.List;

/**
 * 业务屏业务
 * Created by Chris on 2018/5/1.
 */
public interface SetBizScreenService {

    /**
     * 保存业务屏设置
     *
     * @param setBizScreenInfo
     * @return
     */
    String setBizScreen(SetBizScreenInfoBo setBizScreenInfo);

    /**
     * 修改业务平设置
     *
     * @param setBizScreenInfo
     * @return
     */
    RestApiMsg modifyBizScreenSetting(SetBizScreenInfoBo setBizScreenInfo);


    /**
     * 查询业务数据
     *
     * @return
     */
    List<SetBizScreenInfoPojo> queryBizScreenSet();


    /**
     * 设置biz Screen
     *
     * @param bizScreenIdList
     * @return
     */
    String settingsBizScreen(List<Integer> bizScreenIdList);

    /**
     * 删除业务屏数据
     *
     * @param bizScreenId
     * @return
     */
    RestApiMsg clearBizScreen(String bizScreenId);

}
