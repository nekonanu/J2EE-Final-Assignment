package cn.edu.nju.controller.jsonData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-8
 * Time: 下午9:38
 * To change this template use File | Settings | File Templates.
 */
public class ProductAddData {
    private String productName;
    private String productPrice;
    private String productNum;
    private String storeName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<String[]> errors(){
        List<String[]> results=new ArrayList<String[]>();
        Pattern integerPattern = Pattern.compile("^[-\\+]?[\\d]*$");
        Pattern doublePattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        if(!doublePattern.matcher(productPrice).matches()){
            String[] str=new String[2];
            str[0]="priceNotValid";
            str[1]="商品价格只能为数字！";
            results.add(str);
        } else if(!integerPattern.matcher(productNum).matches()){
            String[] str=new String[2];
            str[0]="remainNumNotValid";
            str[1]="剩余商品数量只能为数字！";
            results.add(str);
        }
        return results;
    }
}
