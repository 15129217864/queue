package com.xct.media.queuing.mapper;


import com.xct.media.queuing.pojo.bo.BaseBizBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Chris on 2018/5/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseBizMapperTest {

    @Autowired
    private BaseBizMapper baseBizMapper;

    @Test
    public void setTransactBizRecordTest() {
        BaseBizBo baseBizBo = new BaseBizBo();
        List<BaseBizBo> baseBizList = baseBizMapper.queryBaseBizInfoSet(baseBizBo);

        baseBizList.forEach(System.out::println);
    }
}
