package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.VipCard;
import cn.edu.nju.dao.IVipCardDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class VipCardDaoImpl extends BaseDaoSupport<VipCard> implements IVipCardDao {
    @Override
    public void charge(VipCard vipCard, int amount) {
        vipCard.setRemainAmount(vipCard.getRemainAmount()+amount);
        getSession().update(vipCard);
    }

    @Override
    public void activate(VipCard vipCard) {
        vipCard.setStatus("activate");
        getSession().update(vipCard);
    }

    @Override
    public void freeze(VipCard vipCard) {
        vipCard.setStatus("freeze");
    }

    @Override
    public List<Object[]> statisticRegister(int store_id) {
        Session session=getSession();
        Query query=session.createQuery("select count(v.id),v.registerDate from VipCard v where v.holder.store.id=:storeID group by v.registerDate");
        query.setInteger("storeID",store_id);
        List result=query.list();
        Iterator iterator=result.iterator();
        List returnList=new ArrayList();
        while (iterator.hasNext()){
            Object[] datas=new Object[2];
            Object[] tuple= (Object[]) iterator.next();
            datas[0]=(Long)tuple[0];
            datas[1]=(Date)tuple[1];
            returnList.add(datas);
        }
        return returnList;
    }
}
