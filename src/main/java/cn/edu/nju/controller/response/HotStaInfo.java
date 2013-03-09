package cn.edu.nju.controller.response;

import cn.edu.nju.bean.Product;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
public class HotStaInfo {
    private Product product;
    private int order_num;
    private int sale_num;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public int getSale_num() {
        return sale_num;
    }

    public void setSale_num(int sale_num) {
        this.sale_num = sale_num;
    }
}
