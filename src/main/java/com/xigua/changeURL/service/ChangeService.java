package com.xigua.changeURL.service;

import com.xigua.changeURL.model.Type;
import com.xigua.changeURL.model.LongShort;

import java.util.Map;

/**
 * @Author: miaomiao
 * @Date: 2018/10/30
 * @Description:
 **/
public interface ChangeService {

    Map<String, Boolean> saveUrl(LongShort longShort);

    String changeUrl(LongShort longShort, Type type, int number,String key);

    String getLongUrlAndUpdateCount(String shortUrl);

    int getCount(String shortUrl);
}
