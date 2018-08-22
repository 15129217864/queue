package com.xct.media.queuing.service.impl;

import com.xct.media.queuing.mapper.BaseBizMapper;
import com.xct.media.queuing.pojo.bo.BaseBizBo;
import com.xct.media.queuing.service.BaseBizService;
import com.xct.media.queuing.util.RestApiMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/5/2 13:11.
 * @Description:
 */
@Service
public class BaseBizServiceImpl implements BaseBizService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseBizMapper baseBizMapper;

    @Override
    public RestApiMsg saveBaseBiz(BaseBizBo baseBizBo) {
        logger.info("save get param baseBizBo value ---{}---", baseBizBo.toString());
        RestApiMsg restApiMsg = new RestApiMsg();
        restApiMsg.setMessage("false");
        int i = baseBizMapper.setBaseBizInfo(baseBizBo);
        if (i == 1) {
            restApiMsg.setMessage("ok");
        }
        return restApiMsg;
    }

    @Override
    public List<BaseBizBo> selectBaseBiz() {
        logger.info("service query base biz all info.");
        BaseBizBo baseBizBo = new BaseBizBo();
        return baseBizMapper.queryBaseBizInfoSet(baseBizBo);
    }

    @Override
    public RestApiMsg updateBaseBiz(BaseBizBo baseBizBo) {
        logger.info("update get param baseBizBo value ---{}---", baseBizBo.toString());
        RestApiMsg restApiMsg = new RestApiMsg();
        restApiMsg.setMessage("false");
        int i = baseBizMapper.updateBaseBizInfo(baseBizBo);
        if (i == 1) {
            restApiMsg.setMessage("ok");
        }
        return restApiMsg;
    }

    @Override
    public RestApiMsg clearBaseBiz(String baseBizId) {
        RestApiMsg restApiMsg = new RestApiMsg();
        if (StringUtils.isNotBlank(baseBizId)) {
            Integer convertBaseBizId = NumberUtils.createInteger(baseBizId);
            BaseBizBo baseBizBo = new BaseBizBo();
            baseBizBo.setBase_biz_id(convertBaseBizId);
            baseBizBo.setHidden(0);
            restApiMsg = updateBaseBiz(baseBizBo);
        }
        return restApiMsg;
    }
}
