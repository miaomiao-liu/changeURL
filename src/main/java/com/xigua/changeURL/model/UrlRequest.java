package com.xigua.changeURL.model;

/**
 * @Author: miaomiao
 * @Date: 2018/10/30
 * @Description:
 **/
public class UrlRequest {
    private String longUrl;

    //访问时，若为空则是系统生成短地址，否则为自定义短地址
    private String shortUrl;

    //字符集选择
    private Type type;

    //短链接位数选择
    private String number;

    private String key;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
