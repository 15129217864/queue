package com.xct.media.queuing.controller;

import com.xct.media.queuing.pojo.SetBizScreenInfoPojo;
import com.xct.media.queuing.pojo.bo.BaseBizBo;
import com.xct.media.queuing.service.BaseBizService;
import com.xct.media.queuing.service.SetBizScreenService;
import com.xct.media.queuing.util.RestApiMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 基础业务控制器
 *
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 21:49.
 * @Description:
 */
@RestController
@RequestMapping("/queue/biz")
public class BizInfoController {

    @Autowired
    private SetBizScreenService setBizScreenService;

    @Autowired
    private BaseBizService baseBizService;

    private Logger logger = LoggerFactory.getLogger(BizInfoController.class);

    /**
     * 获取所有业务设置信息
     *
     * @return
     */
    @RequestMapping(value = "/getBizScreen", method = RequestMethod.GET)
    public List<SetBizScreenInfoPojo> getAllBizScreenInfo() {
        return setBizScreenService.queryBizScreenSet();
    }

    /**
     * 保存业务基础数据
     *
     * @param bizName
     * @param bizNum
     * @return
     */
    @RequestMapping(value = "/saveBaseBiz", method = RequestMethod.POST)
    public RestApiMsg saveBaseBizInfo(String bizName, String bizNum) {
        BaseBizBo baseBizBo = new BaseBizBo();
        baseBizBo.setBase_biz_name(bizName);
        baseBizBo.setBase_biz_num(bizNum);
        return baseBizService.saveBaseBiz(baseBizBo);
    }

    /**
     * 获取所有基础业务
     *
     * @return
     */
    @RequestMapping(value = "/getAllBaseBizInfo", method = RequestMethod.GET)
    public RestApiMsg<List<BaseBizBo>> getAllBaseBizInfo() {
        logger.info("controller  query all base info.");
        List<BaseBizBo> baseBizList = baseBizService.selectBaseBiz();
        RestApiMsg<List<BaseBizBo>> restApiMsg = new RestApiMsg<>();
        restApiMsg.setRestCode(0);
        restApiMsg.setRestData(baseBizList);
        return restApiMsg;
    }

    /**
     * 删除基础业务
     *
     * @return
     */
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public RestApiMsg clearBaseBize(@RequestParam("id") String baseBizId) {
        logger.info("controller  clear base info {}.", baseBizId);
        return baseBizService.clearBaseBiz(baseBizId);
    }

    /**
     * 修改基础业务数据
     *
     * @return
     */
    @RequestMapping(value = "/updateBaseBiz", method = RequestMethod.POST)
    public RestApiMsg updateBaseBizInfo(BaseBizBo baseBizBo) {
        return baseBizService.updateBaseBiz(baseBizBo);
    }
}
