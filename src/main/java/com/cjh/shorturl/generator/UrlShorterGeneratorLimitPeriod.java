package com.cjh.shorturl.generator;

import com.cjh.shorturl.ShorterStorage;
import com.cjh.shorturl.StringGenerator;
import com.cjh.shorturl.UrlShorterGenerator;
import com.cjh.shorturl.shorter.ShorterWithPeriod;

/**
 * 用于生成指定长度的串,限制访问次数
 * chenjunhan on 2019/11/12
 */
public class UrlShorterGeneratorLimitPeriod implements UrlShorterGenerator<ShorterWithPeriod> {

    private StringGenerator generator;
    private ShorterStorage<ShorterWithPeriod> shorterStorage;
    /**
     * 有效时长，单位秒
     */
    private long period;

    public StringGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(StringGenerator generator) {
        this.generator = generator;
    }

    public ShorterStorage<ShorterWithPeriod> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterWithPeriod> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }


    public ShorterWithPeriod generate(String url) {
        String shorter = generator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = generator.generate(url);
        }
        ShorterWithPeriod shorterWithPeriod = new ShorterWithPeriod(shorter, period);
        shorterStorage.save(url, shorterWithPeriod);
        return shorterWithPeriod;
    }

}
