package cn.edu.nju.dao;

import cn.edu.nju.bean.User;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午7:22
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {
    public void addUser(User user);
    public void deleteUserByID(int id);
    public User findUserByID(int id);
}