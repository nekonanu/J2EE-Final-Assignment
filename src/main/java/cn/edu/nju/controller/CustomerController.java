package cn.edu.nju.controller;

import cn.edu.nju.controller.validation.CustomerSignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

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

    @RequestMapping(value = "signUp",method = RequestMethod.GET)
    public String signUp(Map model){
        CustomerSignUpForm customerSignUpForm=new CustomerSignUpForm();
        model.put( "CustomerSignUpForm",customerSignUpForm);
        return "/customer/signUp";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "/customer/login";
    }

    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String postSignUp(@Valid CustomerSignUpForm customerSignUpForm,
                             BindingResult result){
        if(result.hasErrors()){
            System.out.println("HAHAHA!");
            return "/customer/signUp";
        }
        System.out.println("FUCK YOU!");
        return "/customer/signUpSuccess";
    }
}
