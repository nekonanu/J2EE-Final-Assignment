package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.Order;
import cn.edu.nju.dao.HibernateUtil;
import cn.edu.nju.dao.OrderDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-2
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public class OrderDaoImpl  implements OrderDao{
    @Override
    public void addOrder(Order order) {
        Session session= HibernateUtil.currentSession();
        Transaction transaction=session.beginTransaction();
        session.save(order);
        transaction.commit();
        HibernateUtil.closeSession();
    }
}
