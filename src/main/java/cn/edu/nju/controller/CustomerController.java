package cn.edu.nju.controller;

import cn.edu.nju.bean.*;
import cn.edu.nju.controller.jsonData.CustomerOrder;
import cn.edu.nju.controller.jsonData.CustomerOrderList;
import cn.edu.nju.controller.validation.CustomerSignUpForm;
import cn.edu.nju.dao.HibernateUtil;
import cn.edu.nju.service.IProductService;
import cn.edu.nju.service.IStoreService;
import cn.edu.nju.service.IUserService;
import cn.edu.nju.service.IVipCardService;
import cn.edu.nju.util.JsonUtil;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @Autowired
    private IUserService userService;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IVipCardService vipCardService;
    @Autowired
    private IProductService productService;
    private CustomerOrder customerOrders;


    @RequestMapping("/test")
    public String test(){
//        Store store=storeService.findByID(1);
//        Set<User> userSet=store.getUsers();
//        System.out.println(userSet.size());
        Product product=productService.findByID(4);
        Set<ProductOrder> productOrders=product.getProductOrder();
        System.out.println(productOrders.size());
        return "/customer/home";
    }
//    Ajax请求的page
    @RequestMapping(value = "/order",method = RequestMethod.GET)
    public String orderPage(Model model){
        User user=userService.findUserByName(getUserName());
        productService.getAvailableProduct(user.getStore().getId(),model,"productRecords");
//        Set<Product> availableProduct = productService.getAvailableProduct(user.getStore().getId());
//        productService.getAvailableProduct(user.getStore().getId())
//        productService.createModel("productRecords",model,availableProduct);
//        Set<Product> products= availableProduct;
//        model.addAttribute("productRecords",products);
        return "/customer/order";
    }

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    @ResponseBody
    private Map processOrder(@RequestBody List<CustomerOrder> customerOrderList){
        User user = userService.findUserByName(getUserName());
        for (CustomerOrder order:customerOrderList){
            Product product=productService.findByID(order.getProduct_id());
            productService.orderProduct(product,user,order.getProduct_num());
        }
        Map map=new HashMap();
        map.put("result","success");
        return map;
    }

    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    public String userInfoPage(){
        return "/customer/userInfo";
    }

    @RequestMapping(value = "/charge",method = RequestMethod.GET)
    public String chargePage(){
        return "/customer/charge";
    }

    @RequestMapping(value = "/messageBox",method = RequestMethod.GET)
    public String messageBoxPage(){
        return "/customer/messageBox";
    }

    @RequestMapping("/home")
    public String home(){
        return "/customer/home";
    }


    @RequestMapping(value = "signUp",method = RequestMethod.GET)
    public String signUp(Map model){
        CustomerSignUpForm customerSignUpForm=new CustomerSignUpForm();
        model.put( "customerSignUpForm",customerSignUpForm);
        return "/customer/signUp";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "/customer/login";
    }

    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String postSignUp(@Valid CustomerSignUpForm customerSignUpForm,
                             BindingResult result){
        System.out.println(customerSignUpForm.getUserName());
        if(result.hasErrors()){
            return "/customer/signUp";
        }

        User user=userService.findUserByName(customerSignUpForm.getUserName());
        if(user!=null){
            result.addError(new ObjectError("nameExisted","用户名已存在"));
            return "customer/signUp";
        }

//        验证成功，添加用户到数据库
        User addUser=new User();
        addUser.setUserName(customerSignUpForm.getUserName());
        addUser.setPassword(customerSignUpForm.getPassword());
        addUser.setType(User.CUSTOMER);
        VipCard vipCard=new VipCard(addUser);
        vipCardService.addVipCard(vipCard);
        addUser.setVipCard(vipCard);
        addUser.setStore(storeService.findByID(1));//TODO here make the storeID fixed
        userService.addUser(addUser);
        return "/customer/signUpSuccess";
    }

    private String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
