package cn.edu.nju.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午4:51
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "sale")
@Entity
public class Sale implements Serializable {
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

    private double pay;
    @javax.persistence.Column(name = "pay")
    @Basic
    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
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

    private int saleNum;

    @javax.persistence.Column(name = "sale_num")
    @Basic
    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    private Date saleDate;

    @javax.persistence.Column(name = "sale_date")
    @Basic
    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
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

        Sale sale = (Sale) o;

        if (user != sale.user) return false;
        if (id != sale.id) return false;
        if (product != sale.product) return false;
        if (saleNum != sale.saleNum) return false;
        if (store != sale.store) return false;
        if (saleDate != null ? !saleDate.equals(sale.saleDate) : sale.saleDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user==null?0:user.getId());
        result = 31 * result + (product==null?0:product.getId());
        result = 31 * result + saleNum;
        result = 31 * result + (saleDate != null ? saleDate.hashCode() : 0);
        result = 31 * result + (store==null?0:store.getId());
        return result;
    }
}
