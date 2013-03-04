package cn.edu.nju.bean;

import org.hibernate.annotations.Type;

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
public class ProductOrderEntity implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    private int customerId;
//
//    @javax.persistence.Column(name = "customer_id")
//    @Basic
//    public int getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }

    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

//    private int productId;
//
//    @javax.persistence.Column(name = "product_id")
//    @Basic
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }

    private ProductEntity productEntity;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOrderEntity that = (ProductOrderEntity) o;

        if (userEntity != that.userEntity) return false;
        if (id != that.id) return false;
        if (orderNum != that.orderNum) return false;
        if (productEntity != that.productEntity) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userEntity.hashCode();
        result = 31 * result + productEntity.hashCode();
        result = 31 * result + orderNum;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        return result;
    }
}
