package cn.edu.nju.controller.jsonData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
public class UserAddData {
    //没有password属性，因为这里设置为初始密码为123
    public static final String PASSWORD="123";

    private String userName;
    private String email;
    private int age;
    private int sex;
    private String address;
    private String type;
    private int storeID;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public List<String[]> errors(){
        List<String[]> results=new ArrayList<String[]>();
        if(email.equals("")){
            String[] str=new String[2];
            str[0]="emailNotValid";
            str[1]="电子邮件不合法！";
            results.add(str);
        } else if(age<=0){
            String[] str=new String[2];
            str[0]="ageNotValid";
            str[1]="年龄必须为大于等于0的数字";
            results.add(str);
        }
        return results;
    }
}
