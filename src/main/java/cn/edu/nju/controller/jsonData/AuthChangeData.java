package cn.edu.nju.controller.jsonData;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 下午4:57
 * To change this template use File | Settings | File Templates.
 */
public class AuthChangeData {
    private int userID;
    private String type;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
