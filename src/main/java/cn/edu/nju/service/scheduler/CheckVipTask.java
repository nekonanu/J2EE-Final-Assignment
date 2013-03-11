package cn.edu.nju.service.scheduler;

import cn.edu.nju.bean.VipCard;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-11
 * Time: 下午7:10
 * To change this template use File | Settings | File Templates.
 */
public class CheckVipTask {
    @Autowired
    private SessionFactory sessionFactory;

    public void run(){
        Session session=sessionFactory.getCurrentSession();
        Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        Query query=session.createQuery("from VipCard v where v.deadlineDate=:deadDate");
        query.setDate("deadDate",date);
        List<VipCard> cards=query.list();
        for (VipCard card:cards){
            card.setStatus(VipCard.FREEZE);
            session.update(card);
        }
        sessionFactory.close();
    }
}
