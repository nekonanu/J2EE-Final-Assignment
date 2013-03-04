package cn.edu.nju.service;

import cn.edu.nju.bean.ProductEntity;
import cn.edu.nju.bean.UserEntity;
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
    public void addUser(UserEntity user);
    public void deleteUserByID(int id);
    public UserEntity findUserByID(int id);

    /**
     *
     * @param productEntity
     * @param userEntity
     * @param product_num
     */
    public void orderProduct(ProductEntity productEntity,UserEntity userEntity,int product_num);
    public LinkedItem getUserAndCard(int user_id);
    public List getAllUserAndCard();
}
