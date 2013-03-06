package cn.edu.nju.controller;

import cn.edu.nju.bean.User;
import cn.edu.nju.controller.response.UserInfo;
import cn.edu.nju.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-5
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @RequestMapping("/home")
    public String home(){
        return "/customer/home";
    }

    @RequestMapping("/signUp")
    public String signUp(){
        return "/customer/signUp";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "/customer/login";
    }
}
