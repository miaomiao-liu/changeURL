package com.xigua.changeURL.model;

/**
 * @Author: miaomiao
 * @Date: 2018/10/31
 * @Description:
 **/
public enum Type {
    DIGITAL(0),//数字
    SMALLALPHABET(1),//小写字母
    BIGALPHABET(2),//大写字母
    ALPHABET(3),//所有字母
    CHARS(4);//所有字符

    private int value;
    Type(int value){
        this.value = value;
    }
    public int getValue(){
        return value;

    }


}
