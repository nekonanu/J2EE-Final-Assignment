package cn.edu.nju.controller.response;

import cn.edu.nju.bean.User;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-5
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class UserInfo {
    private String userName;
    private String email;
    private VipCardInfo vipCardInfo;
    private int age;
    private String sex;
    private String address;
    private StoreInfo storeInfo;

    public UserInfo(){}

    public UserInfo(User user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.vipCardInfo = new VipCardInfo(user.getVipCard());
        this.age = user.getAge();
        if(user.getSex()==0){
            this.sex = "Woman";
        } else {
            this.sex = "Man";
        }
        this.address = user.getAddress();
        this.storeInfo = new StoreInfo(user.getStore());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VipCardInfo getVipCardInfo() {
        return vipCardInfo;
    }

    public void setVipCardInfo(VipCardInfo vipCardInfo) {
        this.vipCardInfo = vipCardInfo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StoreInfo getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfo storeInfo) {
        this.storeInfo = storeInfo;
    }
}
