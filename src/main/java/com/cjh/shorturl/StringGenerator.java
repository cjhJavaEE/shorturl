package com.cjh.shorturl;

/**
 * 随机字符串发生器
 * chenjunhan on 2019/11/12
 */
public interface StringGenerator {
    String generate(String url);

    void setLength(int length);
}
