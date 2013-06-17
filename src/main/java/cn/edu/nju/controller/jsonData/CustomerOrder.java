package cn.edu.nju.controller.jsonData;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-6-14
 * Time: 上午11:38
 * To change this template use File | Settings | File Templates.
 */
public class CustomerOrder {
    private List<CustomerOrderItem> order_data;
    private String pay_method;

    public List<CustomerOrderItem> getOrder_data() {
        return order_data;
    }

    public void setOrder_data(List<CustomerOrderItem> order_data) {
        this.order_data = order_data;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }
}
