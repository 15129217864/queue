package com.xct.media.queuing.mapper;


import com.xct.media.queuing.pojo.bo.TransactBizRecordBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Chris on 2018/5/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactBizRecordMapperTest {

    @Autowired
    private TransactBizRecordMapper transactBizRecordMapper;

    @Test
    public void setTransactBizRecordTest() {

        TransactBizRecordBo transactBizRecord = new TransactBizRecordBo();
        transactBizRecord.setId_card("610524199909093215");
        transactBizRecord.setUse_name("chris");

        transactBizRecord.setUse_name("0");
        transactBizRecord.setUse_photo("11212udjfkaflkjwiefadfkklsda");
        transactBizRecord.setUse_birty("1999年09月09日");
        transactBizRecord.setUse_addr("上海上海上海上海上海上海上海上海上海上海上海上海");
        transactBizRecord.setUse_current_photo("kfjwiejfawjekfjadnaiwefiang");
        transactBizRecord.setScreen_num("81");
        transactBizRecord.setBiz_name("网上、跨域立案");
        transactBizRecord.setQueue_num("E004");
        int transact_biz_id = transactBizRecordMapper.setTransactBizRecord(transactBizRecord);
    }
}
