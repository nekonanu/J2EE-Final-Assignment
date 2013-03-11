package cn.edu.nju.controller;

import cn.edu.nju.bean.Store;
import cn.edu.nju.bean.User;
import cn.edu.nju.bean.VipCard;
import cn.edu.nju.controller.jsonData.*;
import cn.edu.nju.service.IStoreService;
import cn.edu.nju.service.IUserService;
import cn.edu.nju.service.IVipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/systemManager")
public class SystemManagerController {
    @Autowired
    @Qualifier("userAuthManager")
    protected AuthenticationManager userAuthManager;
    @Autowired
    private IUserService userService;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IVipCardService vipCardService;

    @RequestMapping("/home")
    public String home(Model model){
        List<Store> stores=storeService.getAllStore();
        model.addAttribute("adminStoreRecords",stores);
        return "/systemManager/home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody LoginForm loginForm,
                     HttpServletRequest request, HttpServletResponse response){
        Map map=new HashMap();
        User user=userService.findUserByName(loginForm.getUserName());
        if (user!=null&&user.getType().equals(User.SYSTEM_MANAGER)&&user.getPassword().equals(loginForm.getPassword())){
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

    @RequestMapping(value = "/storeAdd",method = RequestMethod.GET)
    public String storeAdd(){
        return "/systemManager/storeAdd";
    }

    @RequestMapping(value = "/storeAdd",method = RequestMethod.POST)
    @ResponseBody
    public Map storeAdd(@RequestBody List<StoreAddData>  datas){
        Map map=new HashMap();
        for (StoreAddData data:datas){
            Store valid=storeService.findByName(data.getStoreName());
            if (valid!=null){
                map.put("result","fail");
                return map;
            }
        }

        for (StoreAddData data:datas){
            Store store=new Store();
            store.setStoreName(data.getStoreName());
            store.setStoreLocation(data.getStoreLocation());
            storeService.addStore(store);
        }
        map.put("result","success");
        return map;
    }

    @RequestMapping(value = "/storeManage",method = RequestMethod.GET)
    public String storeManage(Model model){
        List<Store> storeSet = storeService.getAllStore();
        model.addAttribute("storeManageRecords",storeSet);
        return "/systemManager/storeManage";
    }

    @RequestMapping(value = "/storeManage",method = RequestMethod.POST)
    @ResponseBody
    public Map storeManage(@RequestBody ChangeStoreData datas){
        Map map=new HashMap();
        if (datas.getOp().equals("change")){
            Store store=storeService.findByID(datas.getStoreID());
            store.setStoreName(datas.getStoreName());
            store.setStoreLocation(datas.getStoreLocation());
            storeService.updateStore(store);
            map.put("result","success");
        }else if (datas.getOp().equals("delete")){
            try {
                storeService.deleteStore(datas.getStoreID());
                map.put("result","success");
            } catch (DataIntegrityViolationException exception){
                map.put("result","fail");
            }
        }else {
            map.put("result","fail");
        }
        return map;
    }

    @RequestMapping(value = "/userAdd",method = RequestMethod.GET)
    public String userAdd(){
        return "/systemManager/userAdd";
    }

    @RequestMapping(value = "/userAdd",method = RequestMethod.POST)
    @ResponseBody
    public Map userAdd(@RequestBody List<UserAddData> datas){
        Map map=new HashMap();
        for (UserAddData data:datas){
            List<String[]> errors=data.errors();
            if (errors.size()!=0){
                map.put("result","fail");
                map.put("errorMessage",data.getUserName()+":"+errors.get(0)[1]);
                return map;
            }
        }

        for (UserAddData data:datas){
            User user=userService.findUserByName(data.getUserName());
            if (user!=null){
                map.put("result","fail");
                map.put("errorMessage", data.getUserName()+":用户名已存在！");
                return map;
            }
        }
        for (UserAddData data:datas){
            User user=new User();
            user.setUserName(data.getUserName());
            user.setPassword(UserAddData.PASSWORD);
            user.setEmail(data.getEmail());
            user.setAge(data.getAge());
            user.setSex(data.getSex());
            user.setAddress(data.getAddress());
            user.setType(data.getType());
            Store store=storeService.findByID(data.getStoreID());
            user.setStore(store);
            VipCard vipCard=new VipCard(user);
            vipCardService.addVipCard(vipCard);
            userService.addUser(user);
        }
        map.put("result","success");
        return map;
    }

    @RequestMapping(value = "/adminManage")
    public String adminManage(@RequestParam("storeName") String storeName,Model model){
        Store store= storeService.findByName(storeName);
        Set<User> storeUsers=store.getUsers();
        model.addAttribute("adminManageRecords",storeUsers);
        return "/systemManager/adminManage";
    }

    @RequestMapping(value = "/processAdminManage")
    @ResponseBody
    public Map adminManage(@RequestBody UserManageData data){
        Map map=new HashMap();
        User valid=userService.findUserByName(getUserName());
        User user=userService.findUserByID(data.getUserID());
        if (user.getId()==valid.getId()){
            map.put("result","fail");
            map.put("errorMessage","不能更改自己的权限！");
            return map;
        }

        List<String[]> errors=data.errors();
        if (errors.size()!=0){
            map.put("result","fail");
            map.put("errorMessage",errors.get(0)[1]);
        }
        user.setType(data.getType());
        VipCard vipCard=user.getVipCard();
        vipCard.setCutoff(Double.parseDouble(data.getCutoff()));
        userService.update(user);
        vipCardService.updata(vipCard);
        map.put("result","success");
        return map;
    }



    private String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
