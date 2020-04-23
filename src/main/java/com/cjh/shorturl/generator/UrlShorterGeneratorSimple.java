package com.cjh.shorturl.generator;

import com.cjh.shorturl.ShorterStorage;
import com.cjh.shorturl.StringGenerator;
import com.cjh.shorturl.UrlShorterGenerator;
import com.cjh.shorturl.shorter.ShorterString;

/**
 * 用于生成指定长度的串
 * chenjunhan on 2019/11/12
 */
public class UrlShorterGeneratorSimple implements UrlShorterGenerator<ShorterString> {

    private StringGenerator generator;
    private ShorterStorage<ShorterString> shorterStorage;

    public ShorterStorage<ShorterString> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterString> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }

    public StringGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(StringGenerator generator) {
        this.generator = generator;
    }

    @Override
    public ShorterString generate(String url) {
        String shorter = generator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = generator.generate(url);
        }
        ShorterString newShorter = new ShorterString(shorter);
        shorterStorage.save(url, newShorter);
        return newShorter;
    }
}
