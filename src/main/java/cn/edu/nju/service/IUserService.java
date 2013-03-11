package cn.edu.nju.service;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.User;
import cn.edu.nju.bean.VipCard;
import cn.edu.nju.controller.response.VipStaRegisterData;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午8:02
 * To change this template use File | Settings | File Templates.
 */
public interface IUserService{
    public void addUser(User user);
    public void deleteUserByID(int id);
    public void update(User user);
    public void changeAuthority(User user,String type);
    public User findUserByID(int id);
    public User findUserByName(String name);

    /**
     *
     * @param product
     * @param user
     * @param product_num
     */
    public void orderProduct(Product product,User user,int product_num);
    public Set<User> getAllUser(int store_id);
    public Set<User> getActiveUsers(int store_id);

    public List<VipStaRegisterData> getRegisterFrequency(int store_id);
}
