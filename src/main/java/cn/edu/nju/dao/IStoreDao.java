package cn.edu.nju.dao;

import cn.edu.nju.bean.Store;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
public interface IStoreDao extends IBaseDao<Store> {
    public Store findByName(String name);
    public List<Store> getAllStore();
}
