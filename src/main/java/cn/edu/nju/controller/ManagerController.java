package cn.edu.nju.controller;

import cn.edu.nju.bean.*;
import cn.edu.nju.controller.jsonData.LoginForm;
import cn.edu.nju.controller.response.HotStaInfo;
import cn.edu.nju.controller.response.OrderStaData;
import cn.edu.nju.controller.response.OrderTypePieData;
import cn.edu.nju.controller.response.VipStaRegisterData;
import cn.edu.nju.service.IProductService;
import cn.edu.nju.service.IStoreService;
import cn.edu.nju.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 上午11:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    @Qualifier("userAuthManager")
    protected AuthenticationManager userAuthManager;
    @Autowired
    private IUserService userService;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IProductService productService;


    @RequestMapping("/home")
    public String home(Model model){
        List<Store> stores=storeService.getAllStore();
        model.addAttribute("managerStoreRecords",stores);
        return "/manager/home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody LoginForm loginForm,
                     HttpServletRequest request, HttpServletResponse response){
        Map map=new HashMap();
        User user=userService.findUserByName(loginForm.getUserName());
        if (user!=null&&user.getType().equals(User.MANAGER)&&user.getPassword().equals(loginForm.getPassword())){
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

    @RequestMapping(value = "/vipSta")
    public String vipStatistics(@RequestParam("storeName") String storeName,Model model){
        Store store= storeService.findByName(storeName);
        Set<User> users=store.getUsers();
        model.addAttribute("vipStaRecords",users);
        Set<User> activeUsers=userService.getActiveUsers(store.getId());
        double active=(double)activeUsers.size()/(double)users.size();
        double freeze=1-active;
        model.addAttribute("vipStaActive",active);
        model.addAttribute("vipStaFreeze",freeze);
        List<VipStaRegisterData> vipStaRegisterDatas=userService.getRegisterFrequency(store.getId());
        model.addAttribute("vipStaRegisterRecords",vipStaRegisterDatas);
        return "/manager/vipSta";
    }

    @RequestMapping(value = "/orderSta")
     public String orderStatistics(@RequestParam("storeName") String storeName,Model model){
        Store store= storeService.findByName(storeName);
        Set<ProductOrder> orders=store.getProductOrders();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        Date begin=calendar.getTime();
        calendar=Calendar.getInstance();
        Date end=calendar.getTime();
        List<OrderStaData> orderStaData=productService.getOrderStaData(begin,end,store.getId());
        model.addAttribute("orderLineRecords",orderStaData);
        model.addAttribute("orderStaRecords",orders);
        List<OrderTypePieData> orderTypePieDatas=productService.getOrderTypePercent(begin,end,store.getId());
        model.addAttribute("orderTypePieRecords",orderTypePieDatas);
        return "/manager/orderSta";
    }

    @RequestMapping(value = "/saleSta")
    public String saleStatistics(@RequestParam("storeName") String storeName,Model model){
        Store store= storeService.findByName(storeName);
        Set<Sale> sales=store.getSales();
        model.addAttribute("saleStaRecords",sales);
        return "/manager/saleSta";
    }

    @RequestMapping(value = "/hotSta")
    public String hotStatistics(@RequestParam("storeName") String storeName,Model model){
        Store store=storeService.findByName(storeName);
        Set<HotStaInfo> info=productService.getHotProducts(store.getId());
        model.addAttribute("hotStaRecords",info);
        return "/manager/hotSta";
    }


    private String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
