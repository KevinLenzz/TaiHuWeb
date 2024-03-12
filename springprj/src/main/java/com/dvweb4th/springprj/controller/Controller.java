package com.dvweb4th.springprj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dvweb4th.springprj.dao.Food;
import com.dvweb4th.springprj.dao.OneReview;
import lombok.Getter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

class FrontReview{
    public String name;
    public String content;
    public double rate;
    public int idx;
}
@RestController
public class Controller {
    @Autowired
    Food food;
    @CrossOrigin
    @RequestMapping("/a")
    public String RespondA(String item){
        if(item.equals("jiangshui")) {
            try {
                Reader rd = new FileReader("src/main/jsons/results/result_precip.json");
                StringBuilder sb = new StringBuilder();
                int n = rd.read();
                while (n != -1) {
                    sb.append((char) n);
                    n = rd.read();
                }
                return sb.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if(item.equals("shuizhi")){
            try {
                Reader rd = new FileReader("src/main/jsons/results/result_wq.json");
                StringBuilder sb = new StringBuilder();
                int n = rd.read();
                while (n != -1) {
                    sb.append((char) n);
                    n = rd.read();
                }
                return sb.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if(item.equals("riverlake")){
            try {
                Reader rd = new FileReader("src/main/jsons/results/result_sw.json");
                StringBuilder sb = new StringBuilder();
                int n = rd.read();
                while (n != -1) {
                    sb.append((char) n);
                    n = rd.read();
                }
                return sb.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else return "failed";
    }
    @RequestMapping("/b")
    public void RespondB(String data){
        JSONObject jsonObject=JSONObject.parseObject(data);
        FrontReview fr=JSONObject.toJavaObject(jsonObject,FrontReview.class);
        OneReview rv=new OneReview(LocalDateTime.now(),fr.name,fr.content, fr.rate,fr.idx);
        food.insert(rv);
        return;
    }
    @RequestMapping("/c")
    public String RespondC(int idx){
        List<OneReview> list=food.select(idx);
        return JSON.toJSONString(list);
    }
}
