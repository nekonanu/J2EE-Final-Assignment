package cn.edu.nju.service.impl;

import cn.edu.nju.bean.UserEntity;
import cn.edu.nju.dao.IUserDao;
import cn.edu.nju.service.UserService;
import cn.edu.nju.util.LinkedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private IUserDao IUserDao;

    public IUserDao getIUserDao() {
        return IUserDao;
    }

    public void setIUserDao(IUserDao IUserDao) {
        this.IUserDao = IUserDao;
    }

    @Override
    public void addUser(UserEntity user) {
        IUserDao.save(user);
    }

    @Override
    public void deleteUserByID(int id) {
        IUserDao.deleteById(id);
    }

    @Override
    public UserEntity findUserByID(int id) {
        return IUserDao.findById(id);
    }

    @Override
    public void orderProduct(int product_id, int user_id, int product_num) {

    }

    @Override
    public LinkedItem getUserAndCard(int user_id) {
        return IUserDao.getUserAndCard(user_id);
    }

    @Override
    public List getAllUserAndCard() {
        return IUserDao.getAllUserAndCard();
    }
}
