package com.dream.admin.controller;

import com.dream.admin.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: huzejun
 * @Date: 2021/4/4 15:16
 */

@Slf4j
@Controller
public class IndexController {

    /**
     * 来登陆页
     * @return
     */
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){
        if (StringUtils.hasLength(user.getUserName()) && "123456".equals(user.getPassword())){
            //把登陆成功的用户保存起来
            session.setAttribute("loginUser",user);
            //登陆成功重定向到main.html; 重定向防止表单重复提交
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","帐号密码错误，请重新输入");
            //回到登陆页面
            return "login";
        }


    }

    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){

        log.info("当前方法是：{}","mainPage");
        /*//是否登陆，拦截器、过滤器机制
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null){
            return "main";
        }else {
            //回到登陆页面
            model.addAttribute("msg","请重新登录");
            return "login";
        }*/

        return "main";

    }
}
