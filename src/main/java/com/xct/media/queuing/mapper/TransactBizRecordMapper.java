package com.xct.media.queuing.mapper;

import com.xct.media.queuing.pojo.bo.TransactBizRecordBo;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 20:41.
 * @Description:
 */
public interface TransactBizRecordMapper {

    /**
     * 保存办理业务数据
     *
     * @param transactBizRecord
     * @return
     */
    int setTransactBizRecord(TransactBizRecordBo transactBizRecord);


    /**
     * 查询办理业务记录数据
     *
     * @param recordId
     * @return
     */
    TransactBizRecordBo queryByTransactBizRecordInfo(int recordId);


    /**
     * 查询办理业务记录数据集
     *
     * @param transactBizRecord
     * @return
     */
    List<TransactBizRecordBo> queryTransactBizRecordSet(TransactBizRecordBo transactBizRecord);

}
