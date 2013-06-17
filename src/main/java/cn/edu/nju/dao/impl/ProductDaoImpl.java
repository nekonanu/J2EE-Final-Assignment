package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.Product;
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
public class ProductDaoImpl extends BaseDaoSupport<Product> implements IProductDao {
    @Override
    public Product findByName(String name) {
        Session session=getSession();
        Query query=session.createQuery("from Product p where p.productName=:name");
        query.setString("name",name);
        List<Product> list=query.list();
        if (list.size()!=0)
            return list.get(0);
        return null;
    }

    @Override
    public List<String> getAllProductType() {
        Session session=getSession();
        Query query=session.createQuery("select distinct p.productType from Product p");
        return query.list();
    }

    @Override
    public List<Product> findByLike(String text,int store_id) {
        Session session = getSession();
        Query query = session.createQuery("from Product p where p.store.id =:storeID and p.productName like :searchText");
//        Query query = session.createQuery("from Product p where p.productName like '%娃娃头%'");
        query.setString("searchText","%"+text+"%");
        query.setInteger("storeID",store_id);
        List<Product> list = query.list();
        return list;
    }

}
