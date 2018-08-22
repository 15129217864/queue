package com.xct.media.queuing.mapper;

import com.xct.media.queuing.pojo.bo.SetBizScreenInfoBo;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 20:41.
 * @Description:
 */
public interface SetBizScreenInfoMapper {

    /**
     * 保存业务数据记录表
     *
     * @param setBizScreenInfo
     * @return
     */
    int setSetBizScreenInfo(SetBizScreenInfoBo setBizScreenInfo);


    /**
     * 查询业务记录数据
     *
     * @param setBizScreenInfo
     * @return
     */
    SetBizScreenInfoBo queryBySetBizScreenInfo(SetBizScreenInfoBo setBizScreenInfo);


    /**
     * 查询业务记录数据集
     *
     * @param setBizScreenInfo
     * @return
     */
    List<SetBizScreenInfoBo> querySetBizScreenInfoSet(SetBizScreenInfoBo setBizScreenInfo);

    /**
     * 根据工作人员id查询出对应的业务屏设置
     *
     * @param jobUserId
     * @return
     */
    SetBizScreenInfoBo querySetBizScreenInfoByUserId(int jobUserId);

    /**
     * 修改业务数据
     *
     * @param setBizScreenInfo
     * @return
     */
    int updateBizScreenInfo(SetBizScreenInfoBo setBizScreenInfo);
}
