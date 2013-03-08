package cn.edu.nju.controller.jsonData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-8
 * Time: 下午12:02
 * To change this template use File | Settings | File Templates.
 */
public class ChargeForm {
    private String chargeNum;

    public String getChargeNum() {
        return chargeNum;
    }

    public void setChargeNum(String chargeNum) {
        this.chargeNum = chargeNum;
    }

    private boolean valid(){
        for (int i = chargeNum.length();--i>=0;){
            if (!Character.isDigit(chargeNum.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public List<String[]> errors(){
        List<String[]> results=new ArrayList<String[]>();
        if (chargeNum.equals("")){
            String[] str=new String[2];
            str[0]=new String("chargeNumValid");
            str[1]=new String("请输入数字！");
            results.add(str);
        }
        return results;
    }
}
