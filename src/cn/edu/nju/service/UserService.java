package cn.edu.nju.service;

import cn.edu.nju.bean.User;

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
}
