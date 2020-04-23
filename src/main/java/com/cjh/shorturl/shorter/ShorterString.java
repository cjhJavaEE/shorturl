package com.cjh.shorturl.shorter;

import com.cjh.shorturl.ShorterGetter;

/**
 * 返回短码和密码
 * chenjunhan on 2019/11/12
 */
public class ShorterString implements ShorterGetter<String> {
    private String shorter;

    public ShorterString() {
    }

    public ShorterString(String shorter) {
        setShorter(shorter);
    }

    public String getShorter() {
        return shorter;
    }

    public void setShorter(String shorter) {
        this.shorter = shorter;
    }


}
