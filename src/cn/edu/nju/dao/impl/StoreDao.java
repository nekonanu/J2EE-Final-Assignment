package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.Store;
import cn.edu.nju.dao.IStoreDao;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class StoreDao extends BaseDaoSupport<Store> implements IStoreDao{
    @Override
    public Store findByName(String name) {
        Session session=getSession();
        Query query=session.createQuery("from Store s where s.storeName=:name");
        query.setString("name",name);
        List<Store> list=query.list();
        if (list.size()!=0)
            return list.get(0);
        return null;
    }
}
