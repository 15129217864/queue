package com.xct.media.queuing.service.impl;

import com.xct.media.queuing.mapper.EvaluateMapper;
import com.xct.media.queuing.mapper.HandleBizRecordMapper;
import com.xct.media.queuing.pojo.DataStatisticsPojo;
import com.xct.media.queuing.pojo.bo.EvaluateBo;
import com.xct.media.queuing.pojo.bo.HandleBizRecordBo;
import com.xct.media.queuing.service.EvaluateRecordService;
import com.xct.media.queuing.util.DataUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Chris on 2018/4/30.
 */
@Service
public class EvaluateRecordServiceImpl implements EvaluateRecordService {


    @Autowired
    private EvaluateMapper evaluateMapper;

    @Autowired
    private HandleBizRecordMapper handleBizRecordMapper;

    private Logger logger = LoggerFactory.getLogger(EvaluateRecordServiceImpl.class);


    /**
     * 评价数据存储
     *
     * @param transactId  第三放数据存储Id
     * @param evaluateVal 评价值 1-好/2-中/3-差
     * @return
     */
    @Override
    public String evaluateRecord(int transactId, String evaluateVal) {
        logger.info("get param transactId [{}] evaluateVal [{}]", transactId, evaluateVal);

        String evaluateDateTime = DateFormatUtils.format(new Date(), dateFromatStr);
        // 查询办理业务记录
        HandleBizRecordBo handleBizRecord = new HandleBizRecordBo();
        handleBizRecord.setTransact_biz_id(transactId);
        HandleBizRecordBo queryHandleBizRecord = handleBizRecordMapper.queryByHandleBizRecord(handleBizRecord);

        EvaluateBo evaluate = new EvaluateBo();
        evaluate.setEvaluate_date(evaluateDateTime);
        evaluate.setHandle_biz_id(queryHandleBizRecord.getHandle_biz_id());
        evaluate.setEvaluate_status("1");
        evaluate.setEvaluate_type(evaluateVal);
        queryHandleBizRecord.setHandle_end_date(evaluateDateTime);


        // 保存评价信息
        int evaluateId = evaluateMapper.insertEvaluateData(evaluate);
        // 更新处理业务时间
        int handleBizRecordId = handleBizRecordMapper.updateHandleBizRecord(queryHandleBizRecord);

        if (evaluateId > 0 && handleBizRecordId > 0) {

            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public List<DataStatisticsPojo> getEvaluateStatistics(DataStatisticsPojo dataStatisticsPojo) {

        int type = dataStatisticsPojo.getType();
        String beginD = dataStatisticsPojo.getBeginDate();
        String date[] = DataUtils.getFirstAndLastOfWeek(beginD);
        switch (type) {
            /*日 0*/

            /*周*/
            case 1:
                beginD = date[0];
                break;
            /*月*/
            case 2:
                beginD = DateFormatUtils.format(new Date(), dateFormatMonth);
                break;
           /*年*/
            case 3:
                beginD = DateFormatUtils.format(new Date(), dateFormatYear);
                break;
        }

        dataStatisticsPojo.setBeginDate(beginD);

        //周
        if (type == 1) {
            dataStatisticsPojo.setEndDate(date[1]);
        }

        List<DataStatisticsPojo> dataStatisticsPojoList = evaluateMapper.evaluateStatistics(dataStatisticsPojo);

        return dataStatisticsPojoList;
    }
}
