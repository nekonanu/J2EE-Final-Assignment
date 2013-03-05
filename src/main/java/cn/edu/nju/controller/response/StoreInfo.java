package cn.edu.nju.controller.response;

import cn.edu.nju.bean.Store;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-5
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class StoreInfo {
    private String storeName;
    private String storeLoacation;

    public StoreInfo(){}

    public StoreInfo(Store store) {
        this.storeName = store.getStoreName();
        this.storeLoacation = store.getStoreLocation();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLoacation() {
        return storeLoacation;
    }

    public void setStoreLoacation(String storeLoacation) {
        this.storeLoacation = storeLoacation;
    }
}
