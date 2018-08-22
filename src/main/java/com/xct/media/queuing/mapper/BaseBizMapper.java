package com.xct.media.queuing.mapper;

import com.xct.media.queuing.pojo.bo.BaseBizBo;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 20:40.
 * @Description:
 */
public interface BaseBizMapper {

    /**
     * 保存业务数据
     *
     * @param baseBiz
     * @return
     */
    int setBaseBizInfo(BaseBizBo baseBiz);

    /**
     * 查询单个业务数据
     *
     * @param bizId
     * @return
     */
    BaseBizBo queryByBaseBizInfo(int bizId);

    /**
     * 查询单个基础业务
     *
     * @param baseBiz
     * @return
     */
    BaseBizBo queryBaseBizInfo(BaseBizBo baseBiz);

    /**
     * 限定条件查询业务数据
     *
     * @param baseBiz
     * @return
     */
    List<BaseBizBo> queryBaseBizInfoSet(BaseBizBo baseBiz);

    /**
     * 修改业务名称
     *
     * @param baseBiz
     * @return
     */
    int updateBaseBizInfo(BaseBizBo baseBiz);
}
