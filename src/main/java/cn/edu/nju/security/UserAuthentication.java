package cn.edu.nju.security;

import cn.edu.nju.bean.User;
import cn.edu.nju.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-5
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
public class UserAuthentication implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userService.findUserByName(s);
        if (user==null){
            throw new UsernameNotFoundException("User ");
        }
        List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(user.getType()));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),
                list);
    }
}
