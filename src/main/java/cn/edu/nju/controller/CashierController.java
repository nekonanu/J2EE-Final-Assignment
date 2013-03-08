package cn.edu.nju.controller;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.bean.User;
import cn.edu.nju.controller.jsonData.ChangeProductData;
import cn.edu.nju.controller.jsonData.LoginForm;
import cn.edu.nju.service.IProductService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-8
 * Time: 下午3:57
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/cashier")
public class CashierController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;

    @Autowired
    @Qualifier("userAuthManager")
    protected AuthenticationManager userAuthManager;



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
        if (user!=null&&user.getType().equals(User.CASHIER)&&user.getPassword().equals(loginForm.getPassword())){
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

    @RequestMapping(value = "/productManage",method = RequestMethod.GET)
    public String productManage(Model model){
        User user=userService.findUserByName(getUserName());
        Set<Product> products=user.getStore().getProducts();
        model.addAttribute("productRecords",products);
        return "/cashier/productManage";
    }

    @RequestMapping(value = "/productManage",method = RequestMethod.POST)
    @ResponseBody
    public Map productManage(@RequestBody ChangeProductData changeProductData){
        Map map=new HashMap();
        if (changeProductData.getOp().equals("change")){
            productService.deleteProductByID(changeProductData.getProductId());
            map.put("result","success");
            return map;
        }
        List<String[]> errors=changeProductData.errors();
        if (errors.size()==0){
            Product product=productService.findByID(changeProductData.getProductId());
            product.setProductName(changeProductData.getProductName());
            product.setPrice(Double.parseDouble(changeProductData.getProductPrice()));
            product.setRemainNum(Integer.parseInt(changeProductData.getProductRemainNum()));
            productService.update(product);
            map.put("result","success");
        }else {
            map.put("result","fail");
        }
        return map;
    }

    @RequestMapping(value = "/sale",method = RequestMethod.GET)
    public String sale(Model model){
        User user=userService.findUserByName(getUserName());
        Set<ProductOrder> orders=user.getStore().getProductOrders();
        model.addAttribute("orderManageRecords",orders);
        return "/cashier/sale";
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        return "/cashier/home";
    }

    private String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
