package com.cjh.shorturl;

/**
 *
 * <p>
 * chenjunhan on 2019/11/12
 */
public interface UrlShorterGenerator<T extends ShorterGetter> {


    /**
     * 产生一个短链接对象
     *
     * @param url
     * @return
     */
    T generate(String url);

}
