package com.xct.media.queuing.mapper;

import com.xct.media.queuing.pojo.DataStatisticsPojo;
import com.xct.media.queuing.pojo.bo.EvaluateBo;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 18:02.
 * @Description:
 */
public interface EvaluateMapper {
    /**
     * 保存人员评价
     *
     * @param evaluate
     * @return
     */
    int insertEvaluateData(EvaluateBo evaluate);

    /**
     * 获取单个人员日周月年的评价 数据量
     *
     * @param dataStatisticsPojo
     * @return
     */
    List<DataStatisticsPojo> evaluateStatistics(DataStatisticsPojo dataStatisticsPojo);
}
