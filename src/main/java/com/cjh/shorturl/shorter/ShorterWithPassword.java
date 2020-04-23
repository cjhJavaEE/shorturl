package com.cjh.shorturl.shorter;

import com.cjh.shorturl.ShorterGetter;

/**
 * 存放短地址和密码
 * chenjunhan on 2019/11/12
 */
public class ShorterWithPassword implements ShorterGetter<ShorterWithPassword> {
    private String shorter;
    private String password;

    public ShorterWithPassword() {
    }

    public ShorterWithPassword(String shorter, String password) {
        setShorter(shorter);
        setPassword(password);
    }

    public String getShorter() {
        return shorter;
    }

    public void setShorter(String shorter) {
        this.shorter = shorter;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
