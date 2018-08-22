package com.xct.media.queuing.mapper;

import com.xct.media.queuing.pojo.bo.HandleBizRecordBo;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 20:41.
 * @Description:
 */
public interface HandleBizRecordMapper {

    /**
     * 保存业务数据记录表
     *
     * @param handlerBizRecord
     * @return
     */
    int setHandleBizRecord(HandleBizRecordBo handlerBizRecord);


    int updateHandleBizRecord(HandleBizRecordBo handlerBizRecord);


    /**
     * 查询业务记录数据
     *
     * @param handleBizRecord
     * @return
     */
    HandleBizRecordBo queryByHandleBizRecord(HandleBizRecordBo handleBizRecord);


    /**
     * 查询业务记录数据集
     *
     * @param handlerBizRecord
     * @return
     */
    List<HandleBizRecordBo> queryHandleBizRecordSet(HandleBizRecordBo handlerBizRecord);
}
