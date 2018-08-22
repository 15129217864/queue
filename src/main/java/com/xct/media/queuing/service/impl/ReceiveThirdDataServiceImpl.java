package com.xct.media.queuing.service.impl;

import com.xct.media.queuing.mapper.*;
import com.xct.media.queuing.pojo.CourtTakeNumPojo;
import com.xct.media.queuing.pojo.PersonPojo;
import com.xct.media.queuing.pojo.bo.*;
import com.xct.media.queuing.service.BasePushService;
import com.xct.media.queuing.service.ReceiveThirdDataService;
import com.xct.media.queuing.storage.StorageService;
import com.xct.media.queuing.util.RestApiMsg;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Chris on 2018/4/30.
 */

@Service
public class ReceiveThirdDataServiceImpl implements ReceiveThirdDataService {

    private Logger logger = LoggerFactory.getLogger(ReceiveThirdDataServiceImpl.class);


    @Autowired
    private TransactBizRecordMapper transactBizRecordMapper;

    @Autowired
    private SetBizScreenInfoMapper setBizScreenInfoMapper;

    @Autowired
    private SetScreenInfoMapper setScreenInfoMapper;

    @Autowired
    private BaseBizMapper baseBizMapper;

    @Autowired
    private HandleBizRecordMapper handleBizRecordMapper;

    @Autowired
    private BasePushService basePushService;

    @Autowired
    private StorageService storageService;

    public RestApiMsg<String> transferThirdData(CourtTakeNumPojo courtTakeNum, HttpServletRequest request) {

        String windowBiz = courtTakeNum.getWindowBiz();
        // 获取第三方用户对象
        PersonPojo person = courtTakeNum.getPerson();

        // 创建接收业务记录对象
        TransactBizRecordBo transactBizRecord = new TransactBizRecordBo();
        transactBizRecord.setId_card(person.getIdCardNum());
        transactBizRecord.setUse_name(person.getUserName());
        switch (person.getGender()) {
            case "男":
                transactBizRecord.setUse_sex("0");
                break;
            case "女":
                transactBizRecord.setUse_sex("1");
                break;
            default:
                // do nothing......
                break;
        }

        String idCardPic = storageService.storeBase64Image(0, person.getIdCardHeadPic());
        String currentPic = storageService.storeBase64Image(1, person.getHeadPic());

        transactBizRecord.setUse_photo(idCardPic);
        transactBizRecord.setUse_birty(person.getBirthday());
        transactBizRecord.setUse_addr(person.getAddress());
        transactBizRecord.setUse_current_photo(currentPic);
        transactBizRecord.setScreen_num(courtTakeNum.getWindowNum());
        transactBizRecord.setBiz_name(windowBiz);
        transactBizRecord.setQueue_num(courtTakeNum.getCurrentQueueNum());

        // 保存推送数据
        int transactBizId = transactBizRecordMapper.setTransactBizRecord(transactBizRecord);

        boolean flag = pushQueueScreenBizDatagram(windowBiz, transactBizRecord);

        // 封装返回结果
        RestApiMsg<String> restApiMsg = new RestApiMsg<>();
        if (flag) {
            restApiMsg.setRestCode(0);
            restApiMsg.setMessage(flag + "");
        } else {
            restApiMsg.setRestCode(1001);
            restApiMsg.setMessage(flag + "");
        }

        return restApiMsg;
    }

    /**
     * 排队叫号数据报文推送
     *
     * @param windowBiz
     * @param transactBizRecord
     * @return
     */
    private boolean pushQueueScreenBizDatagram(String windowBiz, TransactBizRecordBo transactBizRecord) {
        boolean flag = false;
        if (null != transactBizRecord) {
            // query base biz
            BaseBizBo baseBiz = new BaseBizBo();
            baseBiz.setBase_biz_name(windowBiz);
            BaseBizBo queryBaseBiz = baseBizMapper.queryBaseBizInfo(baseBiz);

            checkNullPrint(queryBaseBiz);
            // 查询条屏信息
            SetScreenInfoBo setScreenInfo = new SetScreenInfoBo();
            setScreenInfo.setBase_biz_id(queryBaseBiz.getBase_biz_id());


            // 查询设置业务屏信息
            SetBizScreenInfoBo setBizScreenInfo = new SetBizScreenInfoBo();
            setBizScreenInfo.setBase_biz_id(queryBaseBiz.getBase_biz_id());

            // 获取条屏设置信息
            SetScreenInfoBo queryBizInfo = setScreenInfoMapper.queryBySetScreenInfo(setScreenInfo);
            checkNullPrint(queryBaseBiz);
            // 获取业务屏设置信息
            SetBizScreenInfoBo querySetBizInfo = setBizScreenInfoMapper.queryBySetBizScreenInfo(setBizScreenInfo);
            checkNullPrint(queryBaseBiz);

            String setBizScreenIP = querySetBizInfo.getBiz_screen_ip();
            String setScreenMark = querySetBizInfo.getBiz_screen_mark();
            logger.info("set biz screen ip--{}, mark--{}", setBizScreenIP, setScreenMark);
            basePushService.pushQueueCallOutBiz(setBizScreenIP, setScreenMark, transactBizRecord);

            //条屏ip
            String setScreenIP = queryBizInfo.getScreen_ip();
            logger.info("set striped screen ip--{}", setScreenIP);
            String mark = queryBizInfo.getScreen_mark();
            String screenType = queryBizInfo.getScreen_type();
            // mark + screenType
            String datagramMark = mark + "/" + screenType;
            basePushService.pushQueueStripedScreenBiz(setScreenIP, datagramMark, transactBizRecord);


            // 存储处理业务记录
            HandleBizRecordBo handleBizRecord = new HandleBizRecordBo();
            handleBizRecord.setBase_biz_id(baseBiz.getBase_biz_id());
            handleBizRecord.setTransact_biz_id(transactBizRecord.getTransact_biz_id());
            handleBizRecord.setJob_user_id(setBizScreenInfo.getJob_user_id());
            handleBizRecord.setHandle_start_date(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            handleBizRecordMapper.setHandleBizRecord(handleBizRecord);

            // 获取处理业务id
            int hanldeBizId = handleBizRecord.getHandle_biz_id();
            if (hanldeBizId > 0) {
                flag = true;
            }
        }
        return flag;
    }


    /**
     * check object 是否为null
     *
     * @param object
     */
    private void checkNullPrint(Object object) {
        if (object == null) {
            throw new RuntimeException("current object is null, [" + object + "]");
        }
    }

}
