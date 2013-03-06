package cn.edu.nju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping(value = "/login")
    public String login(){
        return "/employee/login";
    }

    @RequestMapping("/home")
    public String home(){
        return "/employee/home";
    }
}
