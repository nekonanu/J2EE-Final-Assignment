package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.Product;
import cn.edu.nju.dao.HibernateUtil;
import cn.edu.nju.dao.ProductDao;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class ProductDaoImpl implements ProductDao{

    @Override
    public void addProduct(Product product) {
        Session session= HibernateUtil.currentSession();
        Transaction transaction=session.beginTransaction();
        session.save(product);
        transaction.commit();
        HibernateUtil.closeSession();
    }
}
