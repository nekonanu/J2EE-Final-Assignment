package cn.edu.nju.service.impl;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.User;
import cn.edu.nju.dao.IStoreDao;
import cn.edu.nju.dao.IUserDao;
import cn.edu.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午8:02
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IStoreDao storeDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUserByID(int id) {
        userDao.deleteById(id);
    }

    @Override
    public void changeAuthority(User user, int type) {
        userDao.changeAuthority(user, type);
    }

    @Override
    public User findUserByID(int id) {
        return userDao.findById(id);
    }

    @Override
    public void orderProduct(Product product, User user, int product_num) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<User> getAllUser(int store_id) {
        return storeDao.findById(store_id).getUsers();
    }
}
