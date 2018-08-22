package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.DataStatisticsPojo;

import java.util.List;

/**
 * Created by Chris on 2018/4/30.
 */
public interface EvaluateRecordService {


    String dateFromatStr = "yyyy-MM-dd HH:mm:ss";

    String dateFormatYear = "yyyy";

    String dateFormatMonth = "yyyy-MM";

    /**
     * 保存评价数据
     *
     * @param transactId  第三放数据存储Id
     * @param evaluateVal 评价值 1-好/2-中/3-差
     * @return
     */
    String evaluateRecord(int transactId, String evaluateVal);

    /**
     * 获取人员 评价数量
     *
     * @param dataStatisticsPojo
     * @return
     */
    List<DataStatisticsPojo> getEvaluateStatistics(DataStatisticsPojo dataStatisticsPojo);
}
