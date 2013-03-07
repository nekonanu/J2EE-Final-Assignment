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
 * Time: 下午4:08
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

//    @RequestMapping(value = "/userInfo")
//    public String getUserInfo(Model model){
//        User user = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
//        UserInfo userInfo=new UserInfo(user);
//        model.addAttribute("userInfo",userInfo);
//        return "/user/userInfo";
//    }
}
