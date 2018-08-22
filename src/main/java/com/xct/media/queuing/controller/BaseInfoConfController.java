package com.xct.media.queuing.controller;

import com.xct.media.queuing.pojo.SetBizScreenInfoPojo;
import com.xct.media.queuing.pojo.bo.SetBizScreenInfoBo;
import com.xct.media.queuing.pojo.bo.SetScreenInfoBo;
import com.xct.media.queuing.service.SetBizScreenService;
import com.xct.media.queuing.service.SetStripedScreenService;
import com.xct.media.queuing.util.BaseScreenWrapper;
import com.xct.media.queuing.util.RestApiMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/27 21:50.
 * @Description:
 */
@RestController
@RequestMapping("/queue/baseConf")
public class BaseInfoConfController {

    private Logger logger = LoggerFactory.getLogger(BaseInfoConfController.class);

    @Autowired
    private SetStripedScreenService setStripedScreenService;

    @Autowired
    private SetBizScreenService setBizScreenService;


    /**
     * 保存业务屏数据
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addBizScreen", method = RequestMethod.POST)
    public String addBizScreen(SetBizScreenInfoBo setBizScreenInfo) {
        return setBizScreenService.setBizScreen(setBizScreenInfo);
    }

    /**
     * 查询所有业务屏配置信息
     *
     * @return
     */
    @RequestMapping(value = "/queryBizScreen", method = RequestMethod.POST)
    public RestApiMsg<List<SetBizScreenInfoPojo>> queryBizScreenList() {
        RestApiMsg<List<SetBizScreenInfoPojo>> restApiMsg = new RestApiMsg<>();
        List<SetBizScreenInfoPojo> setBizScreenInfoList = setBizScreenService.queryBizScreenSet();

        restApiMsg.setRestCode(0);
        restApiMsg.setRestData(setBizScreenInfoList);

        return restApiMsg;
    }


    /**
     * 保存横条屏设置
     *
     * @param setScreenInfo
     * @return
     */
    @RequestMapping(value = "/addStriped_screen", method = RequestMethod.POST)
    public String addStripedScreen(SetScreenInfoBo setScreenInfo) {
        logger.info("add striped screen {}", setScreenInfo.toString());
        return setStripedScreenService.setStripedScreen(setScreenInfo);
    }

    /**
     * 修改横条屏设置
     *
     * @param setScreenInfo
     * @return
     */
    @RequestMapping(value = "/modifyStriped_screen", method = RequestMethod.POST)
    public RestApiMsg modifyStripedScreen(SetScreenInfoBo setScreenInfo) {

        logger.info("modify striped screen {}", setScreenInfo.toString());
        return setStripedScreenService.modifyStripedScreenSetting(setScreenInfo);
    }

    /**
     * 查询横条屏数据集合
     *
     * @return
     */
    @RequestMapping(value = "/queryStripedScreen", method = RequestMethod.POST)
    public RestApiMsg<List<SetScreenInfoBo>> queryStripedScreen() {
        RestApiMsg<List<SetScreenInfoBo>> restApiMsg = new RestApiMsg<>();
        List<SetScreenInfoBo> setScreenInfoList = setStripedScreenService.queryStripedScreenSet();
        restApiMsg.setRestCode(0);
        restApiMsg.setRestData(setScreenInfoList);
        return restApiMsg;
    }

    /**
     * 查询横条屏数据集合 页面显示
     *
     * @return
     */
    @RequestMapping(value = "/queryStripedScreenSetting", method = RequestMethod.GET)
    public List<SetScreenInfoBo> queryStripedScreenSetting() {
        List<SetScreenInfoBo> list = setStripedScreenService.queryStripedScreenSet();
        return list;
    }

    /**
     * 设置单个业务屏数据推送
     *
     * @param bizScreenIdList
     * @return
     */
    @RequestMapping(value = "/settingsBizScreen", method = RequestMethod.POST)
    public String settingsBizScreen(@RequestBody BaseScreenWrapper bizScreenIdList) {
        logger.info("settings biz screen data {}", bizScreenIdList.toString());
        return setBizScreenService.settingsBizScreen(bizScreenIdList.getBizScreenIdList());
    }

    /**
     * 推送 横条屏数据
     *
     * @param bizScreenIdList
     * @return
     */
    @RequestMapping(value = "/settingsStripedScreen", method = RequestMethod.POST)
    public String settingsStripedScreen(@RequestBody BaseScreenWrapper bizScreenIdList) {
        logger.info("push striped screen  data {}", bizScreenIdList.toString());
        return setStripedScreenService.settingsStripedScreen(bizScreenIdList.getBizScreenIdList());
    }

    /**
     * 删除横条屏数据
     *
     * @param screenId
     * @return
     */
    @RequestMapping(value = "/clearStripedScreen", method = RequestMethod.GET)
    public RestApiMsg clearStripedScreen(@RequestParam("id") String screenId) {
        logger.info("controller  clear striped screen  info {}.", screenId);
        return setStripedScreenService.clearStripedScreen(screenId);
    }

    /**
     * 修改业务屏数据
     *
     * @param setBizScreenInfo
     * @return
     */
    @RequestMapping(value = "/updateBizScreen", method = RequestMethod.POST)
    public RestApiMsg modifyBizScreenSetting(SetBizScreenInfoBo setBizScreenInfo) {
        logger.info("controller update biz screen  info {}.", setBizScreenInfo);
        return setBizScreenService.modifyBizScreenSetting(setBizScreenInfo);
    }

    /**
     * 删除业务屏数据
     *
     * @param bizScreenId
     * @return
     */
    @RequestMapping(value = "/clearBizScreen", method = RequestMethod.GET)
    public RestApiMsg clearBizScreen(@RequestParam("id") String bizScreenId) {
        logger.info("controller  clear biz screen info {}.", bizScreenId);
        return setBizScreenService.clearBizScreen(bizScreenId);
    }
}