package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.bo.BaseBizBo;
import com.xct.media.queuing.util.RestApiMsg;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/5/2 13:11.
 * @Description:
 */
public interface BaseBizService {
    /**
     * 保存业务基础数据
     *
     * @param baseBizBo
     * @return
     */
    RestApiMsg saveBaseBiz(BaseBizBo baseBizBo);

    /**
     * 查询所有基础业务数据
     *
     * @return
     */
    List<BaseBizBo> selectBaseBiz();

    /**
     * 修改基础业务数据
     *
     * @param baseBizBo
     * @return
     */
    RestApiMsg updateBaseBiz(BaseBizBo baseBizBo);

    /**
     * 删除基础业务数据
     *
     * @param baseBizId
     * @return
     */
    RestApiMsg clearBaseBiz(String baseBizId);

}
