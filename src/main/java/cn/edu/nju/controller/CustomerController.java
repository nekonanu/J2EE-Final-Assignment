package cn.edu.nju.controller;

import cn.edu.nju.bean.*;
import cn.edu.nju.controller.jsonData.CustomerOrder;
import cn.edu.nju.controller.jsonData.ChargeForm;
import cn.edu.nju.controller.jsonData.LoginForm;
import cn.edu.nju.controller.validation.CustomerSignUpForm;
import cn.edu.nju.service.IProductService;
import cn.edu.nju.service.IStoreService;
import cn.edu.nju.service.IUserService;
import cn.edu.nju.service.IVipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    @Qualifier("userAuthManager")
    protected AuthenticationManager userAuthManager;


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
        Set<Product> products= productService.getAvailableProduct(user.getStore().getId());
        model.addAttribute("productRecords",products);
        return "/customer/order";
    }

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    @ResponseBody
    public Map processOrder(@RequestBody List<CustomerOrder> customerOrderList){
        Map map=new HashMap();
        User user = userService.findUserByName(getUserName());
        for (CustomerOrder order:customerOrderList){
            if (!order.isNumberValid()){
                map.put("result","fail");
                map.put("errorMessage","预定数量必须大于0");
                return map;
            }
        }
        if (productService.customerCanAfford(customerOrderList,user)){
            for (CustomerOrder order:customerOrderList){
                Product product=productService.findByID(order.getProduct_id());
                productService.orderProduct(product,user,order.getProduct_num());
                vipCardService.buyByCard(user,product.getPrice()*order.getProduct_num());
            }
            map.put("result","success");
            map.put("infoMessage","恭喜您！订购成功！");
        }else{
            map.put("result","fail");
            map.put("errorMessage","你的账户余额不足！");
        }
        return map;
    }

    /**
     * 查看已订购
     * @param model
     * @return
     */
    @RequestMapping(value = "/orderInfo",method = RequestMethod.GET)
    public String orderInfo(Model model){
        User user=userService.findUserByName(getUserName());
        Set<ProductOrder> orders=user.getProductOrderEntities();
        model.addAttribute("orderInfoRecords",orders);
        return "/customer/orderInfo";
    }



    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    public String userInfoPage(Model model){
        User user=userService.findUserByName(getUserName());
        model.addAttribute("userInfoRecord",user);
        return "/customer/userInfo";
    }

    @RequestMapping(value = "/charge",method = RequestMethod.GET)
    public String chargePage(Model model,Map map){
        User user=userService.findUserByName(getUserName());
        VipCard vipCard=user.getVipCard();
        ChargeForm chargeForm=new ChargeForm();
        map.put( "chargeForm",chargeForm);
        model.addAttribute("charge_vipCardRecord",vipCard);
        model.addAttribute("charge_storeRecord",user.getStore());
        return "/customer/charge";
    }

    @RequestMapping(value = "/charge",method = RequestMethod.POST)
    @ResponseBody
    public Map processCharge(@RequestBody ChargeForm chargeForm){
        List<String[]> errors=chargeForm.errors();
        Map map=new HashMap();
        if (errors.size()==0){
            User user=userService.findUserByName(getUserName());
            vipCardService.cardCharge(user,Integer.parseInt(chargeForm.getChargeNum()));
            map.put("result","success");
        }else {
            map.put("result","fail");
            for (int index=0;index<errors.size();index++){
                map.put("errorType"+index,errors.get(index)[0]);
                map.put("errorMessage"+index,errors.get(index)[1]);
            }
        }
        return map;
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

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "/customer/login";
    }

    /**
     * ajax登录验证
     * @param loginForm
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody LoginForm loginForm,
                     HttpServletRequest request, HttpServletResponse response){
        Map map=new HashMap();
        User user=userService.findUserByName(loginForm.getUserName());
        if (user!=null&&user.getPassword().equals(user.getPassword())){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUserName(), loginForm.getPassword());
            request.getSession();
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authentication = userAuthManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            map.put("result","success");
        }else{
            map.put("result","fail");
        }
        return map;
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
