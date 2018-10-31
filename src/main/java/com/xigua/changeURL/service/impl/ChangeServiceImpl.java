package com.xigua.changeURL.service.impl;

import com.xigua.changeURL.dao.LongShortDao;
import com.xigua.changeURL.model.Type;
import com.xigua.changeURL.model.LongShort;
import com.xigua.changeURL.service.ChangeService;
import com.xigua.changeURL.util.ChangeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: miaomiao
 * @Date: 2018/10/30
 * @Description:
 **/
@Service
public class ChangeServiceImpl implements ChangeService {

    @Autowired
    LongShortDao longShortDao;


    @Override
    public Map<String, Boolean> saveUrl(LongShort longShort) {
        Map<String,Boolean> result = new HashMap<>();
        if(longShortDao.findByShortUrl(longShort.getShortUrl()) != null){
            result.put("success",false);
            return result;
        }
        longShortDao.save(longShort);
        result.put("success",true);
        return result;
    }


    @Override
    public String changeUrl(LongShort longShort, Type type, int number,String key) {
        String[] shortUrls = ChangeUtil.convert(longShort.getLongUrl(),type,number,key);
        for (String shortUrl : shortUrls){
            longShort.setShortUrl(shortUrl);
            if (saveUrl(longShort).get("success")){
                return shortUrl;
            }
        }
        return null;
    }

    @Transactional
    @Override
    public String getLongUrlAndUpdateCount(String shortUrl) {
        LongShort longShort = longShortDao.findByShortUrl(shortUrl);
        if (longShort != null){
            longShortDao.updateCount(longShort.getId());
            return longShort.getLongUrl();
        }
        return null;
    }

    @Override
    public int getCount(String shortUrl) {
        return longShortDao.getCountByShortUrl(shortUrl);
    }


}
