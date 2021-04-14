package com.dream.boot.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @Author: huzejun
 * @Date: 2021/3/27 0:01
 */

@RestController
public class HelloController {

    @RequestMapping("/企业01.jpg")
    public String hello(){
        return "aaa";
    }

//    @RequestMapping(value = "user",method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser(){
        return "GET-张三";
    }

//    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping("/user")
    public String saveUser(){
        return "POST-张三";
    }

//    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PutMapping("/user")
    public String putUser(){
        return "PUT-张三";
    }

//    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    @DeleteMapping(value = "/user")
    public String deleteUser(){
        return "DELETE-张三";
    }
}
