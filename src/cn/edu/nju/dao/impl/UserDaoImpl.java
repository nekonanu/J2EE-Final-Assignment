package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.UserEntity;
import cn.edu.nju.bean.VipCardEntity;
import cn.edu.nju.dao.HibernateUtil;
import cn.edu.nju.dao.UserDao;
import cn.edu.nju.util.LinkedItem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public void addUser(UserEntity user) {
        Session session= HibernateUtil.currentSession();
        Transaction tx=session.beginTransaction();
        session.save(user);
        tx.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public void deleteUserByID(int id) {
        Session session=HibernateUtil.currentSession();
        Transaction transaction=session.beginTransaction();
        session.delete("id",id);
        transaction.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public UserEntity findUserByID(int id) {
        Session session=HibernateUtil.currentSession();
        Query query=session.createQuery("from UserEntity u where u.id =:id");
        query.setInteger("id",id);
        List<UserEntity> list=query.list();
        if (list.size()!=0)
            return list.get(0);
        HibernateUtil.closeSession();
        return null;
    }

    @Override
    public LinkedItem getUserAndCard(int user_id) {
        Session session=HibernateUtil.currentSession();
        Query query=session.createQuery("from UserEntity u, VipCardEntity v where u.id=v.holderId and u.id=:userID");
        query.setInteger("userID",user_id);
        UserEntity user=null;
        VipCardEntity vipCard=null;
        List list=query.list();
        if(list.size()!=0){
            Object[] objects= (Object[]) list.get(0);
            for (int j=0;j<objects.length;j++){
                if (objects[j] instanceof UserEntity)
                    user= (UserEntity) objects[j];
                else if (objects[j] instanceof VipCardEntity)
                    vipCard= (VipCardEntity) objects[j];
            }
        }
        LinkedItem<UserEntity> item1=new LinkedItem<UserEntity>(user);
        LinkedItem<VipCardEntity> item2=new LinkedItem<VipCardEntity>(vipCard);
        item1.setLinkedItem(item2);
        HibernateUtil.closeSession();
        return item1;
    }

    @Override
    public List getAllUserAndCard() {
        Session session=HibernateUtil.currentSession();
        Query query=session.createQuery("from UserEntity u, VipCardEntity v where u.id=v.holderId");
        UserEntity user=null;
        VipCardEntity vipCard=null;
        List list=query.list();
        List<LinkedItem> resultList=new ArrayList<LinkedItem>();
        if(list.size()!=0){
            for (int i=0;i<list.size();i++){
                Object[] objects= (Object[]) list.get(i);
                for (int j=0;j<objects.length;j++){
                    if (objects[j] instanceof UserEntity)
                        user= (UserEntity) objects[j];
                    else if (objects[j] instanceof VipCardEntity)
                        vipCard= (VipCardEntity) objects[j];
                }
                LinkedItem<UserEntity> item1=new LinkedItem<UserEntity>(user);
                LinkedItem<VipCardEntity> item2=new LinkedItem<VipCardEntity>(vipCard);
                item1.setLinkedItem(item2);
                resultList.add(item1);
            }
        }
        HibernateUtil.closeSession();
        return resultList;
    }
}
