package com.xct.media.queuing.mapper;

import com.xct.media.queuing.pojo.bo.SetScreenInfoBo;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 20:41.
 * @Description:
 */
public interface SetScreenInfoMapper {

    /**
     * 保存业务数据记录表
     *
     * @param setScreenInfo
     * @return
     */
    int setSetScreenInfo(SetScreenInfoBo setScreenInfo);


    /**
     * 修改横条屏数据
     *
     * @param setScreenInfo
     * @return
     */
    int updateSetScreenInfo(SetScreenInfoBo setScreenInfo);


    /**
     * 查询业务记录数据
     *
     * @param setScreenInfo
     * @return
     */
    SetScreenInfoBo queryBySetScreenInfo(SetScreenInfoBo setScreenInfo);


    /**
     * 查询业务记录数据集
     *
     * @param setScreenInfo
     * @return
     */
    List<SetScreenInfoBo> querySetScreenInfoSet(SetScreenInfoBo setScreenInfo);
}
