package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.VipCard;
import cn.edu.nju.dao.HibernateUtil;
import cn.edu.nju.dao.VipCardDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
public class VipCardDaoImpl implements VipCardDao {
    @Override
    public void addVipCard(VipCard card) {
        Session session= HibernateUtil.currentSession();
        Transaction tx=session.beginTransaction();
        session.save(card);
        tx.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public void deleteVipCard(int id) {
        Session session=HibernateUtil.currentSession();
        Transaction transaction=session.beginTransaction();
        session.delete("id",id);
        transaction.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public VipCard findVipCardByID(int id) {
        Session session=HibernateUtil.currentSession();
        Query query=session.createQuery("from VipCard v where v.id =:id");
        query.setInteger("id",id);
        List<VipCard> list=query.list();
        if (list.size()!=0)
            return list.get(0);
        HibernateUtil.closeSession();
        return null;
    }
}
