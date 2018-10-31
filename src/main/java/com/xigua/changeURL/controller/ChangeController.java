package com.xigua.changeURL.controller;

import com.xigua.changeURL.model.Type;
import com.xigua.changeURL.model.LongShort;
import com.xigua.changeURL.model.Result;
import com.xigua.changeURL.model.UrlRequest;
import com.xigua.changeURL.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: miaomiao
 * @Date: 2018/10/30
 * @Description:
 **/
@Controller
public class ChangeController {

    @Autowired
    ChangeService changeService;

    @RequestMapping(value = "/getShortUrl", method = {RequestMethod.POST})
    @ResponseBody
    public Result getShortUrl(UrlRequest urlRequest) {
        LongShort longShort = new LongShort();
        longShort.setLongUrl(urlRequest.getLongUrl());
        longShort.setAccessCount(0);
        //采用自定义短链接
        if(urlRequest.getShortUrl() != null && !"".equals(urlRequest.getShortUrl().trim())){
            longShort.setShortUrl(urlRequest.getShortUrl());
            if (changeService.saveUrl(longShort).get("success") == false){
                return new Result("自定义短地址已被占用");
            }else {
                return new Result("短地址为：" + urlRequest.getShortUrl());
            }
        }else {
        //服务器生成短链接
            Type type = urlRequest.getType() == null ? Type.CHARS : urlRequest.getType();
            int number = Integer.parseInt(urlRequest.getNumber()) == 0 ? 4 : Integer.parseInt(urlRequest.getNumber());
            String result = changeService.changeUrl(longShort,type,number,urlRequest.getKey());
            if (result == null){
                return new Result("短地址生成失败");
            }else {
                return new Result("短地址为：" + result);
            }
        }
    }

    @RequestMapping(value = "/{shortUrl:[0-9a-zA-Z]{4,16}}",method = RequestMethod.GET)
    public Object accessShortUrl(@PathVariable("shortUrl") String shortUrl){
        String longUrl = changeService.getLongUrlAndUpdateCount(shortUrl);
        if (longUrl == null){
            return new Result("没有该短地址对应的长地址");
        }
        return new ModelAndView("redirect:" + longUrl);
    }


    @RequestMapping(value = "/accessCount",method = RequestMethod.GET)
    @ResponseBody
    public Result getCount(String shortUrl){
        return new Result("访问次数为：" + changeService.getCount(shortUrl));
    }

}
