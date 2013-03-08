package cn.edu.nju.controller.jsonData;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-7
 * Time: 下午7:11
 * To change this template use File | Settings | File Templates.
 */
public class CustomerOrder {
    private int product_id;
    private int product_num;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_num() {
        return product_num;
    }

    public void setProduct_num(int product_num) {
        this.product_num = product_num;
    }

    public boolean isNumberValid(){
        if (product_num<=0)
            return false;
        else
            return true;
    }
}
