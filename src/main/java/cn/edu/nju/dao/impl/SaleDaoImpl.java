package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.bean.Sale;
import cn.edu.nju.dao.ISaleDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SaleDaoImpl extends BaseDaoSupport<Sale> implements ISaleDao{
    @Override
    public boolean has(int id) {
        Query query=getSession().createQuery("select sa.id from Sale sa where sa.id=id");
        List<Integer> ids=query.list();
        if (ids.size()==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<Sale> findBetweenDate(Date begin, Date end, int store_id) {
        Session session=getSession();
        Query query=session.createQuery("from Sale s where s.saleDate>=:beginDate and s.saleDate<=:endDate and s.store.id=:storeID");
        query.setDate("beginDate",begin);
        query.setInteger("storeID",store_id);
        query.setDate("endDate",end);
        List list=query.list();
        return list;
    }
}
