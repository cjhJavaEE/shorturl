package com.cjh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @ClassName: com.cjh.ShortUrlApplication
 * @Description: springboot启动类
 * @Author: chenjunhan
 * @CreateDate: 2019/11/12 9:06
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ShortUrlApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }
}
