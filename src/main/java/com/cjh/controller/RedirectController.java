package com.cjh.controller;

import com.cjh.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: RedirectController
 * @Description: 短连接重定向
 * @Author: chenjunhan
 * @CreateDate: 2019/11/12 9:16
 */
@Controller
public class RedirectController {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 短链接跳转原始地址
     *
     * @param request
     * @return
     */
    @RequestMapping("/{link}")
    public void alipayforward(HttpServletRequest request, HttpServletResponse response, @PathVariable("link")String link) throws Exception {
        String longUrl = "";
        String redisKey = "shorturl:msginfo:"+link;
        boolean exist = redisUtils.hasKey(redisKey);
        if(exist){
            longUrl = redisUtils.get(redisKey);
        }
        if(!longUrl.contains("http://") && !longUrl.contains("https://")){
            response.sendRedirect("http://" + longUrl);
        }else {
            response.sendRedirect(longUrl);
        }

    }
}
