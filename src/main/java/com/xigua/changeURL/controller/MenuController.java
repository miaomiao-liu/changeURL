package com.xigua.changeURL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: miaomiao
 * @Date: 2018/10/30
 * @Description:
 **/
@Controller
public class MenuController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
