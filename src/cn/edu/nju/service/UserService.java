package cn.edu.nju.service;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.User;
import cn.edu.nju.util.LinkedItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午8:02
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    public void addUser(User user);
    public void deleteUserByID(int id);
    public User findUserByID(int id);

    /**
     *
     * @param product
     * @param user
     * @param product_num
     */
    public void orderProduct(Product product,User user,int product_num);
    public LinkedItem getUserAndCard(int user_id);
    public List getAllUserAndCard();
}
