package com.cjh.shorturl.shorter;

import com.cjh.shorturl.ShorterGetter;

/**
 * 存放短地址和访问次数
 * chenjunhan on 2019/11/12
 */
public class ShorterWithTimes implements ShorterGetter<ShorterWithTimes> {
    private String shorter;
    private long times;

    public ShorterWithTimes() {
    }
    public ShorterWithTimes(String shorter, long times) {
        setShorter(shorter);
        setTimes(times);
    }

    public String getShorter() {
        return shorter;
    }

    public void setShorter(String shorter) {
        this.shorter = shorter;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

}
