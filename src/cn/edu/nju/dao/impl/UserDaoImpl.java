package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.User;
import cn.edu.nju.bean.VipCard;
import cn.edu.nju.dao.IUserDao;
import cn.edu.nju.util.LinkedItem;
import org.hibernate.Query;
import org.hibernate.Session;
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
public class UserDaoImpl extends BaseDaoSupport<User> implements IUserDao {
    @Override
    public LinkedItem getUserAndCard(int user_id) {
        Session session=getSession();
        Query query=session.createQuery("from User u, VipCard v where u.id=v.holderId and u.id=:userID");
        query.setInteger("userID",user_id);
        User user=null;
        VipCard vipCard=null;
        List list=query.list();
        if(list.size()!=0){
            Object[] objects= (Object[]) list.get(0);
            for (int j=0;j<objects.length;j++){
                if (objects[j] instanceof User)
                    user= (User) objects[j];
                else if (objects[j] instanceof VipCard)
                    vipCard= (VipCard) objects[j];
            }
        }
        LinkedItem<User> item1=new LinkedItem<User>(user);
        LinkedItem<VipCard> item2=new LinkedItem<VipCard>(vipCard);
        item1.setLinkedItem(item2);
        return item1;
    }

    @Override
    public List getAllUserAndCard() {
        Session session=getSession();
        Query query=session.createQuery("from User u, VipCard v where u.id=v.holderId");
        User user=null;
        VipCard vipCard=null;
        List list=query.list();
        List<LinkedItem> resultList=new ArrayList<LinkedItem>();
        if(list.size()!=0){
            for (int i=0;i<list.size();i++){
                Object[] objects= (Object[]) list.get(i);
                for (int j=0;j<objects.length;j++){
                    if (objects[j] instanceof User)
                        user= (User) objects[j];
                    else if (objects[j] instanceof VipCard)
                        vipCard= (VipCard) objects[j];
                }
                LinkedItem<User> item1=new LinkedItem<User>(user);
                LinkedItem<VipCard> item2=new LinkedItem<VipCard>(vipCard);
                item1.setLinkedItem(item2);
                resultList.add(item1);
            }
        }
        return resultList;
    }
}
