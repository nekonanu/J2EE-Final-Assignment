package cn.edu.nju.controller.jsonData;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
public class ChangeStoreData {
    private int storeID;
    private String storeName;
    private String storeLocation;
    private String op;

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
