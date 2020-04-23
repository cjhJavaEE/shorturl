package com.cjh.test;

/**
 * @ClassName: com.cjh.test.server_test
 * @Description: 短连接当成一个单独项目部署，可以通过main方法中调用方式，在你所需要生成短连接的项目中请求短连接项目。
 * @Author: ChenJunHan
 * @CreateDate: 2020/4/23 16:17
 */
public class server_test {

    public static void main(String[] args) {
        String server_url = "你的短连接服务地址";
        String url = DwzUtil.createShortUrl("www.baidu.com", "1-year", server_url);

    }
}
