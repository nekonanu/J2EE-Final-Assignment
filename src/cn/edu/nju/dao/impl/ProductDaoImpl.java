package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.ProductEntity;
import cn.edu.nju.util.HibernateUtil;
import cn.edu.nju.dao.IProductDao;
import org.hibernate.Query;
import org.hibernate.Session;
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
public class ProductDaoImpl extends BaseDaoSupport<ProductEntity> implements IProductDao {
    @Override
    public ProductEntity findByName(String name) {
        Session session=getSession();
        Query query=session.createQuery("from ProductEntity p where p.productName=:name");
        query.setString("name",name);
        List<ProductEntity> list=query.list();
        if (list.size()!=0)
            return list.get(0);
        return null;
    }

    @Override
    public List<ProductEntity> getAllAvailableProduct() {
        Session session=getSession();
        Query query=session.createQuery("from ProductEntity p");
        List<ProductEntity> list=query.list();
        HibernateUtil.closeSession();
        return list;
    }
}
