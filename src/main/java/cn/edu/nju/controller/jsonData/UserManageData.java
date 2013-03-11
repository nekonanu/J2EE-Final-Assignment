package cn.edu.nju.controller.jsonData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 下午4:57
 * To change this template use File | Settings | File Templates.
 */
public class UserManageData {
    private int userID;
    private String cutoff;
    private String type;

    public String getCutoff() {
        return cutoff;
    }

    public void setCutoff(String cutoff) {
        this.cutoff = cutoff;
    }

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

    public List<String[]> errors(){
        List<String[]> results=new ArrayList<String[]>();
        Pattern doublePattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        if(!doublePattern.matcher(cutoff).matches()){
            String[] str=new String[2];
            str[0]="cutoffNotValid";
            str[1]="折扣率格式错误！";
            results.add(str);
        }
        return results;
    }
}
