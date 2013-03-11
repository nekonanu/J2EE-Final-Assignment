package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.dao.IOrderDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-2
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class OrderDaoImpl extends BaseDaoSupport<ProductOrder> implements IOrderDao {
    @Override
    public List<ProductOrder> findBetweenDate(Date begin, Date end,int store_id) {
        Session session=getSession();
        Query query=session.createQuery("from ProductOrder po where po.orderDate>=:beginDate and po.orderDate<=:endDate and po.store.id=:storeID");
        query.setDate("beginDate",begin);
        query.setInteger("storeID",store_id);
        query.setDate("endDate",end);
        List list=query.list();
        return list;
    }
}
