package cn.edu.nju.dao.impl;

import cn.edu.nju.dao.IBaseDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 上午11:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseDaoSupport<T extends Serializable> implements IBaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    public BaseDaoSupport(){
        //        Try to get T.class
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public void save(T t) {
        getSession().save(t);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public void deleteById(int i) {
        getSession().delete("id",i);
    }

    @Override
    public T findById(int i) {
        return (T) getSession().get(getPersistentClass(),i);
    }

    @Override
    public List<T> findByCriteria(Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria.list();
    }
}
