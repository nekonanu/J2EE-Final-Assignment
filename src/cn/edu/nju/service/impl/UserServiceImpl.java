package cn.edu.nju.service.impl;

import cn.edu.nju.bean.User;
import cn.edu.nju.dao.UserDao;
import cn.edu.nju.service.UserService;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午8:02
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUserByID(int id) {
        userDao.deleteUserByID(id);
    }

    @Override
    public User findUserByID(int id) {
        return userDao.findUserByID(id);
    }
}
