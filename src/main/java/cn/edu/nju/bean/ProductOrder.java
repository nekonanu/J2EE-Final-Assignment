package cn.edu.nju.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "product_order", schema = "", catalog = "dessert_house")
@Entity
public class ProductOrder implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private int orderNum;

    @javax.persistence.Column(name = "order_num")
    @Basic
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    private Date orderDate;

    @javax.persistence.Column(name = "order_date")
    @Basic
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    private String orderCheck;

    @javax.persistence.Column(name = "order_check")
    @Basic
    public String getOrderCheck() {
        return orderCheck;
    }

    public void setOrderCheck(String orderCheck) {
        this.orderCheck = orderCheck;
    }




    private Store store;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOrder that = (ProductOrder) o;

        if (user != that.user) return false;
        if (id != that.id) return false;
        if (orderNum != that.orderNum) return false;
        if (product != that.product) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user==null?0:user.getId());
        result = 31 * result + (product==null?0:product.getId());
        result = 31 * result + orderNum;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        return result;
    }
}
