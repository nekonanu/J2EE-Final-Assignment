package cn.edu.nju.bean;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:41
 * To change this template use File | Settings | File Templates.
 */
public class Product {
    private int id;
    private String product_name;
    private int remainal_num;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getRemainal_num() {
        return remainal_num;
    }

    public void setRemainal_num(int remainal_num) {
        this.remainal_num = remainal_num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
