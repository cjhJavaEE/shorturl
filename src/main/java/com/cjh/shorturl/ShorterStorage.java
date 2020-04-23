package com.cjh.shorturl;

/**
 * 用来存储字符串短地址,针对不同的generator需要有不同的存储器
 * chenjunhan on 2019/11/12
 */
public interface ShorterStorage<T extends ShorterGetter> {

    String get(String shorter);

    void clean(String url);

    void cleanShorter(String shorter);

    void save(String url, T shorter);

    void clean();

}
