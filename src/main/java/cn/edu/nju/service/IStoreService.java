package cn.edu.nju.service;

import cn.edu.nju.bean.Store;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
public interface IStoreService {
    public void addStore(Store store);
    public Store findByName(String name);
    public void updateStore(Store store);
    public void deleteStore(int id);
    public Store findByID(int id);
    public List<Store> getAllStore();
}
