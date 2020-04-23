package com.cjh.entity;

/**
 * @ClassName: UrlResponse
 * @Description: 返回类
 * @Author: chenjunhan
 * @CreateDate: 2019/11/12 13:22
 */
public class UrlResponse {

    private int code;

    private String errMsg;

    private String longUrl;

    private String shortUrl;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
