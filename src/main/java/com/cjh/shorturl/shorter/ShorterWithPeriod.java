package com.cjh.shorturl.shorter;

import com.cjh.shorturl.ShorterGetter;

/**
 * 存放短地址和超时时间
 * chenjunhan on 2019/11/12
 */
public class ShorterWithPeriod implements ShorterGetter<ShorterWithPeriod> {
    private String shorter;
    private long period;

    public ShorterWithPeriod() {
    }
    public ShorterWithPeriod(String shorter, long period) {
        setShorter(shorter);
        setPeriod(period);
    }

    @Override
    public String getShorter() {
        return shorter;
    }

    public void setShorter(String shorter) {
        this.shorter = shorter;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

}
