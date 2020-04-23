package com.cjh.shorturl.generator;

import com.cjh.shorturl.ShorterStorage;
import com.cjh.shorturl.StringGenerator;
import com.cjh.shorturl.UrlShorterGenerator;
import com.cjh.shorturl.shorter.ShorterWithPeriodAndTimes;

/**
 * 用于生成指定长度的串,限制访问次数
 * chenjunhan on 2019/11/12
 */
public class UrlShorterGeneratorLimitPeriodAndTimes implements UrlShorterGenerator<ShorterWithPeriodAndTimes> {
    private StringGenerator generator;
    private ShorterStorage<ShorterWithPeriodAndTimes> shorterStorage;
    /**
     * 有效时长，单位秒
     */
    private long period;
    /**
     * 最多使用次数
     */
    private long times;

    public StringGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(StringGenerator generator) {
        this.generator = generator;
    }

    public ShorterStorage<ShorterWithPeriodAndTimes> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterWithPeriodAndTimes> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public ShorterWithPeriodAndTimes generate(String url) {
        String shorter = generator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = generator.generate(url);
        }
        ShorterWithPeriodAndTimes shorterWithPeriodAndTimes = new ShorterWithPeriodAndTimes(shorter, period, times);
        shorterStorage.save(url, shorterWithPeriodAndTimes);
        return shorterWithPeriodAndTimes;
    }

}
