package cn.edu.nju.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-2
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public class Order {
    private int id;
    private int customer_id;
    private int product_id;
    private int order_num;
    private Date order_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
}
