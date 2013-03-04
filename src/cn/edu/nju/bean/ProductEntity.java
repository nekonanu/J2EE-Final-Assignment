package cn.edu.nju.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "product", schema = "", catalog = "dessert_house")
@Entity
public class ProductEntity implements Serializable {
    private Set<ProductOrderEntity> productOrderEntity;

    @OneToMany(mappedBy = "productEntity")
    public Set<ProductOrderEntity> getProductOrderEntity() {
        return productOrderEntity;
    }

    public void setProductOrderEntity(Set<ProductOrderEntity> productOrderEntity) {
        this.productOrderEntity = productOrderEntity;
    }

    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String productName;

    @javax.persistence.Column(name = "product_name")
    @Basic
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private int remainNum;

    @javax.persistence.Column(name = "remain_num")
    @Basic
    public int getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }

    private double price;

    @javax.persistence.Column(name = "price")
    @Basic
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (remainNum != that.remainNum) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + remainNum;
        temp = price != +0.0d ? Double.doubleToLongBits(price) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
