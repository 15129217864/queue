package com.xct.media.queuing.controller;

import com.xct.media.queuing.pojo.DataStatisticsPojo;
import com.xct.media.queuing.service.EvaluateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 10:02.
 * @Description:
 */
@RestController
@RequestMapping("/queue/evaluate")
public class EvaluateInfoController {

    @Autowired
    private EvaluateRecordService evaluateRecordService;

    /**
     * 根据条件获取 评价数据
     *
     * @param dataStatisticsPojo
     * @return
     */
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public List<DataStatisticsPojo> getData(DataStatisticsPojo dataStatisticsPojo) {
        return evaluateRecordService.getEvaluateStatistics(dataStatisticsPojo);
    }
}
