package cn.edu.nju.controller;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.bean.Store;
import cn.edu.nju.bean.User;
import cn.edu.nju.controller.jsonData.ChangeProductData;
import cn.edu.nju.controller.jsonData.LoginForm;
import cn.edu.nju.controller.jsonData.ProductAddData;
import cn.edu.nju.controller.jsonData.SaleData;
import cn.edu.nju.service.IProductService;
import cn.edu.nju.service.IStoreService;
import cn.edu.nju.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    private IStoreService storeService;
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
        StandardPasswordEncoder encoder=new StandardPasswordEncoder("secret");
        if (user!=null&&user.getType().equals(User.CASHIER)&&encoder.matches(loginForm.getPassword(),user.getPassword())){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUserName(), user.getPassword());
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

    @RequestMapping(value = "/productAdd",method = RequestMethod.GET)
    public String productAdd(Model model){
        List<Store> stores=storeService.getAllStore();
        List<String> productType=productService.getAllProductType();
        model.addAttribute("productTypeRecords",productType);
        model.addAttribute("storeRecords",stores);
        return "/cashier/productAdd";
    }

    @RequestMapping(value="/fileUpload", method = RequestMethod.POST)
    public @ResponseBody Map upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        Map map=new HashMap();
        MultipartFile file = request.getFile(request.getFileNames().next());
        String fileName= file.getOriginalFilename();
        File filePath=new File(request.getSession().getServletContext().getRealPath("/WEB-INF/static/img/"+fileName));
        try {
            FileCopyUtils.copy(file.getBytes(),filePath);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("result","fail");
            return map;
        }
        map.put("result","success");
        return map;
    }



    @RequestMapping(value = "/productAdd",method = RequestMethod.POST)
    @ResponseBody
    public Map productAdd(@RequestBody List<ProductAddData> productAddDatas){
        Map map=new HashMap();
        for (ProductAddData data:productAddDatas){
            List<String[]> errors=data.errors();
            if (errors.size()!=0){
                map.put("result","fail");
                map.put("errorType","numberValid");
                return map;
            }
        }
        for (ProductAddData data:productAddDatas){
            Product product=new Product();
            product.setProductName(data.getProductName());
            product.setRemainNum(Integer.parseInt(data.getProductNum()));
            product.setPrice(Double.parseDouble(data.getProductPrice()));
            Store store=storeService.findByName(data.getStoreName());
            product.setStore(store);
            product.setProductType(data.getProductType());
            product.setImgPath("/dessertHouse/static/img/"+data.getImgPath());
            productService.addProduct(product);
            map.put("result","success");
        }
        return map;
    }

    @RequestMapping(value = "/productManage",method = RequestMethod.GET)
    public String productManage(Model model){
        User user=userService.findUserByName(getUserName());
        Set<Product> products=user.getStore().getProducts();
        model.addAttribute("productRecords",products);
        List<String> productType=productService.getAllProductType();
        model.addAttribute("productTypeRecords",productType);
        return "/cashier/productManage";
    }

    @RequestMapping(value = "/productManage",method = RequestMethod.POST)
    @ResponseBody
    public Map productManage(@RequestBody ChangeProductData changeProductData){
        Map map=new HashMap();
        if (changeProductData.getOp().equals("delete")){
            try {
                productService.deleteProductByID(changeProductData.getProductId());
                map.put("result","success");
            } catch (DataIntegrityViolationException exception){
                map.put("result","fail");
            }
            return map;
        }
        List<String[]> errors=changeProductData.errors();
        if (errors.size()==0){
            Product product=productService.findByID(changeProductData.getProductId());
            product.setProductName(changeProductData.getProductName());
            product.setPrice(Double.parseDouble(changeProductData.getProductPrice()));
            product.setRemainNum(Integer.parseInt(changeProductData.getProductRemainNum()));
            product.setProductType(changeProductData.getProductType());
            productService.update(product);
            map.put("result","success");
        }else {
            map.put("result","fail");
        }
        return map;
    }


    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        return "/cashier/home";
    }

    private String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
