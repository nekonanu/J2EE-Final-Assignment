package cn.edu.nju.controller.jsonData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-8
 * Time: 下午6:25
 * To change this template use File | Settings | File Templates.
 */
public class ChangeProductData {
    private int productId;
    private String productName;
    private String productPrice;
    private String productRemainNum;
    private String productType;
    private String op;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

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

    public String getProductRemainNum() {
        return productRemainNum;
    }

    public void setProductRemainNum(String productRemainNum) {
        this.productRemainNum = productRemainNum;
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
        } else if(!integerPattern.matcher(productRemainNum).matches()){
            String[] str=new String[2];
            str[0]="remainNumNotValid";
            str[1]="剩余商品数量只能为数字！";
            results.add(str);
        }
        return results;
    }
}
