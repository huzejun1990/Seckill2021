package com.dream.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.admin.bean.User;
import com.dream.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Author: huzejun
 * @Date: 2021/4/4 17:27
 */
@Controller
public class TableController {

    @Autowired
    UserService userService;

    /**
     *
     * @param a 不带请求参数，或者参数类型不对 400 Bad Request 一般都是浏览器的参数没有传递正确
     * @return
     */
    @GetMapping("/basic_table")
    public String basic_table(@RequestParam("a") int a){

        int i = 10/0;
        return "table/basic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestParam(value = "pn",defaultValue = "1") Integer pn,
                             RedirectAttributes redirectAttributes){

        userService.removeById(id);

        redirectAttributes.addAttribute("pn", pn);

        return "redirect:/dynamic_table";

    }

    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //表格内容的遍历
/*        List<User> users = Arrays.asList(new User("zhangsan", "123456"),
                new User("lisi", "123444"),
                new User("haha", "aaa"),
                new User("hehe", "aaadddd"));

        if ((users.size()>3)){
            throw new UserTooManyException();
        }

        model.addAttribute("users",users);*/
        //从数据库中查出user表中的用户进行展示

        List<User> list = userService.list();

//        model.addAttribute("users",list);

        //分页查询出的数据
        Page<User> userPage = new Page<>(pn, 2);

        //分页查询的结果
        Page<User> page = userService.page(userPage, null);
        long current = page.getCurrent();
        long pages = page.getPages();
        long total = page.getTotal();
        List<User> records = page.getRecords();

        model.addAttribute("page",page);

        return "table/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editable_table(){
        return "/table/editable_table";
    }
}
