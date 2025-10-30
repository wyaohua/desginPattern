package com.yaohua.designpattern.controller;

import com.yaohua.designpattern.策略模式.IdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private IdGenerate idGenerate;


    @GetMapping("/test01/{name}")
    public String test策略模式(@PathVariable("name") String name) throws IllegalAccessException {

        return idGenerate.getId(name);

    }
}
