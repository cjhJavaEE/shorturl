package com.cjh.controller;

import cn.hutool.json.JSONObject;
import com.cjh.entity.UrlResponse;
import com.cjh.shorturl.generator.StringGeneratorRandom;
import com.cjh.shorturl.generator.UrlShorterGeneratorSimple;
import com.cjh.shorturl.shorter.ShorterString;
import com.cjh.shorturl.storage.ShorterStorageMemory;
import com.cjh.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: CreateUrlController
 * @Description: 创建短网址
 * @Author: chenjunhan
 * @CreateDate: 2019/11/12 9:47
 */
@RestController
@RequestMapping("/create")
public class CreateUrlController {

    @Autowired
    private RedisUtils redisUtils;
    @Value("${dwz.link}")
    private String link;

    @PostMapping("/shortUrl")
    public UrlResponse shortUrl(HttpServletRequest request, HttpServletResponse response,@RequestBody JSONObject jsonObject) {
        String token = request.getHeader("Token");
        UrlResponse ur = new UrlResponse();
        //短连接token可自由设置
        if(token == null || !"chenJunHanShortUrlToken".equals(token)){
            ur.setCode(-1);
            ur.setErrMsg("token不正确");
            return ur;
        }
        String TermOfValidity = jsonObject.getStr("TermOfValidity");
        String Url = jsonObject.getStr("Url");
        String shorter = "";
        String redisKey = "";
        while (true){
            shorter = generateUrl();
            redisKey = "shorturl:info:"+shorter;
            if(!redisUtils.hasKey(redisKey)){
                break;
            }
        }
        if("1-year".equals(TermOfValidity)){
            //生成的短连接 和 原网址存redis 设置过期时间一年
            redisUtils.setObject(redisKey, Url);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR,1);
            Date time = calendar.getTime();
            redisUtils.expireAt(redisKey, time);
        }else{
            //生成的短连接 和 原网址存redis 不设置过期时间
            redisUtils.setObject(redisKey, Url);
        }
        shorter = link + "/" + shorter;
        ur.setLongUrl(Url);
        ur.setShortUrl(shorter);
        ur.setCode(0);
        return ur;
    }

    private String generateUrl(){
        UrlShorterGeneratorSimple simple = new UrlShorterGeneratorSimple();
        simple.setGenerator(new StringGeneratorRandom(8));
        simple.setShorterStorage(new ShorterStorageMemory<ShorterString>());
        String shorter = simple.generate("").getShorter();
        return shorter;
    }

}
