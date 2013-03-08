package cn.edu.nju.controller;

import cn.edu.nju.bean.User;
import cn.edu.nju.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-5
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IUserService userService;
//
//    @RequestMapping(value = "/login")
//    public String login(){
//        return "/employee/login";
//    }

//    @RequestMapping("/home")
//    public String home(){
//        User user=userService.findUserByName(getUserName());
//        if (user.getType().equals(User.CASHIER)){
//            return "/cashier/home";
//        }else if (user.getType().equals(User.MANAGER)){
//            return "manager/home";
//        }else if (user.getType().equals(User.SYSTEM_MANAGER)){
//            return "system/home";
//        }
//        return "customer/home";
//    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "employee/login";
    }

    private String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
