package cn.edu.nju.service.impl;

import cn.edu.nju.bean.Store;
import cn.edu.nju.dao.IStoreDao;
import cn.edu.nju.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午6:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class StoreServiceImpl implements IStoreService {
    @Autowired
    private IStoreDao storeDao;

    @Override
    public void addStore(Store store) {
        storeDao.save(store);
    }

    @Override
    public void updateStore(Store store) {
        storeDao.update(store);
    }

    @Override
    public void deleteStore(int id) {
        storeDao.deleteById(id);
    }

    @Override
    public Store findByID(int id) {
        return storeDao.findById(id);
    }
}
