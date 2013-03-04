package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.ProductOrderEntity;
import cn.edu.nju.dao.OrderDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-2
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class OrderDaoImpl  implements OrderDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrder(ProductOrderEntity order) {
        sessionFactory.getCurrentSession().save(order);
    }
}
