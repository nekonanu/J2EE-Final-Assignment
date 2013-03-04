package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.ProductEntity;
import cn.edu.nju.dao.HibernateUtil;
import cn.edu.nju.dao.ProductDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ProductDaoImpl implements ProductDao{

    @Override
    public void addProduct(ProductEntity product) {

        Session session= HibernateUtil.currentSession();
        Transaction transaction=session.beginTransaction();
        session.save(product);
        transaction.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public void deleteProductByID(int id) {
        Session session=HibernateUtil.currentSession();
        Transaction transaction=session.beginTransaction();
        session.delete("id",id);
        transaction.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public ProductEntity findByName(String name) {
        Session session=HibernateUtil.currentSession();
        Query query=session.createQuery("from Product p where p.product_name=:name");
        query.setString("name",name);
        List<ProductEntity> list=query.list();
        if (list.size()!=0)
            return list.get(0);
        HibernateUtil.closeSession();
        return null;
    }

    @Override
    public ProductEntity findByID(int id) {
        Session session=HibernateUtil.currentSession();
        Query query=session.createQuery("from Product p where p.id=:id");
        query.setInteger("id",id);
        List<ProductEntity> list=query.list();
        if (list.size()!=0)
            return list.get(0);
        HibernateUtil.closeSession();
        return null;
    }

    @Override
    public List<ProductEntity> getAllAvailableProduct() {
        Session session=HibernateUtil.currentSession();
        Query query=session.createQuery("from Product p");
        List<ProductEntity> list=query.list();
        HibernateUtil.closeSession();
        return list;
    }


}
