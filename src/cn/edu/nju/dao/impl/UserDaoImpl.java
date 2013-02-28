package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.User;
import cn.edu.nju.dao.HibernateUtil;
import cn.edu.nju.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl implements UserDao{
    @Override
    public void addUser(User user) {
        Session session= HibernateUtil.currentSession();
        Transaction tx=session.beginTransaction();
        session.save(user);
        tx.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public void deleteUserByID(int id) {
        Session session=HibernateUtil.currentSession();
        Transaction transaction=session.beginTransaction();
        session.delete("id",id);
        transaction.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public User findUserByID(int id) {
        Session session=HibernateUtil.currentSession();
        Query query=session.createQuery("from User u where u.id =:id");
        query.setInteger("id",id);
        List<User> list=query.list();
        if (list.size()!=0)
            return list.get(0);
        return null;
    }
}
