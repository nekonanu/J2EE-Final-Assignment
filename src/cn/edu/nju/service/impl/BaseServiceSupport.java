package cn.edu.nju.service.impl;

import cn.edu.nju.dao.IBaseDao;
import cn.edu.nju.dao.impl.BaseDaoSupport;
import cn.edu.nju.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午6:06
 * To change this template use File | Settings | File Templates.
 */

public abstract class BaseServiceSupport<T extends Serializable> implements IBaseService<T>{
    private Class<T> persistentClass;

//    @Autowired
//    @Qualifier("baseDaoSupport")

    private IBaseDao<T> baseDao;

    public BaseServiceSupport(){
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public void save(T t) {
        baseDao.save(t);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public void deleteById(int i) {
        baseDao.deleteById(i);
    }

    @Override
    public T findById(int i) {
        return baseDao.findById(i);
    }
}
