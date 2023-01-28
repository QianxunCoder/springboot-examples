package com.github.codeqingkong.springdataredis.redisson.controller;

import com.github.codeqingkong.springdataredis.redisson.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: QingKong
 * @date: 2023/1/28
 */
@RestController
@RequestMapping("/redisson")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @PostMapping("/add")
    public void add(){
        helloService.insert();
    }

    @PutMapping("/update")
    public void update(){
        helloService.update("name", "CodeQingKong");
    }

    @DeleteMapping("/delete")
    public void remove(){
        helloService.delete("name");
    }

    @GetMapping("/get")
    public String get(){
       return helloService.get("name");
    }

}
