package com.cjh.shorturl.generator;

import com.cjh.shorturl.ShorterStorage;
import com.cjh.shorturl.StringGenerator;
import com.cjh.shorturl.UrlShorterGenerator;
import com.cjh.shorturl.shorter.ShorterWithPassword;

/**
 * 用于生成指定长度的串
 * chenjunhan on 2019/11/12
 */
public class UrlShorterGeneratorWithPassword implements UrlShorterGenerator<ShorterWithPassword> {

    private StringGenerator shorterGenerator;
    private StringGenerator passwordGenerator;
    private ShorterStorage<ShorterWithPassword> shorterStorage;

    public StringGenerator getShorterGenerator() {
        return shorterGenerator;
    }

    public void setShorterGenerator(StringGenerator shorterGenerator) {
        this.shorterGenerator = shorterGenerator;
    }

    public StringGenerator getPasswordGenerator() {
        return passwordGenerator;
    }

    public void setPasswordGenerator(StringGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

    public ShorterStorage<ShorterWithPassword> getShorterStorage() {
        return shorterStorage;
    }

    public void setShorterStorage(ShorterStorage<ShorterWithPassword> shorterStorage) {
        this.shorterStorage = shorterStorage;
    }


    public ShorterWithPassword generate(String url) {
        String shorter = shorterGenerator.generate(url);
        while (shorterStorage.get(shorter) != null) {
            shorter = shorterGenerator.generate(url);
        }
        ShorterWithPassword shorterWithPassword = new ShorterWithPassword(shorter, passwordGenerator.generate(url));
        shorterStorage.save(url, shorterWithPassword);
        return shorterWithPassword;
    }


}
