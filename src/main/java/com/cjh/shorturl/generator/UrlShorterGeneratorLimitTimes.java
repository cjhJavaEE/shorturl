package com.cjh.shorturl.generator;

import com.cjh.shorturl.ShorterStorage;
import com.cjh.shorturl.StringGenerator;
import com.cjh.shorturl.UrlShorterGenerator;
import com.cjh.shorturl.shorter.ShorterWithTimes;

/**
 * 用于生成指定长度的串,限制访问次数
 * chenjunhan on 2019/11/12
 */
public class UrlShorterGeneratorLimitTimes implements UrlShorterGenerator<ShorterWithTimes> {

    private StringGenerator generator;
    private ShorterStorage<ShorterWithTimes> shorterStorage;
    /**
     * 有效时长，单位秒
     */
    private long times;

    public StringGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(StringGenerator generator) {
        this.generator = generator;
    }

    public ShorterStorage<ShorterWithTimes> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterWithTimes> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }


    public ShorterWithTimes generate(String url) {
        String shorter = generator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = generator.generate(url);
        }
        ShorterWithTimes shorterWithPeriod = new ShorterWithTimes(shorter, times);
        shorterStorage.save(url, shorterWithPeriod);
        return shorterWithPeriod;
    }

}
