package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.User;
import cn.edu.nju.bean.VipCard;
import cn.edu.nju.dao.IUserDao;
import cn.edu.nju.util.LinkedItem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDaoImpl extends BaseDaoSupport<User> implements IUserDao {
    @Override
    public void changeAuthority(User user, int type) {
        Session session=getSession();
        user.setType(type);
        session.update(user);
    }
}
