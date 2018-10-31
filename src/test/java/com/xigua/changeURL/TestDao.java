package com.xigua.changeURL;

import com.xigua.changeURL.dao.LongShortDao;
import com.xigua.changeURL.model.LongShort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: miaomiao
 * @Date: 2018/10/30
 * @Description:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDao {

    @Autowired
    LongShortDao longShortDao;

    @Test
    public void test(){
        LongShort longShort = new LongShort();
        longShort.setLongUrl("nkvbri;v");
        longShort.setShortUrl("123");
        longShortDao.save(longShort);
    }

}
