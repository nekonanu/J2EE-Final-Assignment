package cn.edu.nju.bean;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Store implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String storeName;

    @javax.persistence.Column(name = "store_name")
    @Basic
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    private String storeLocation;

    @javax.persistence.Column(name = "store_location")
    @Basic
    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    private Set<Product> products;
    @OneToMany(mappedBy = "store")
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    private Set<ProductOrder> productOrders;
    @OneToMany(mappedBy = "store")
    public Set<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(Set<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    private Set<User> users;
    @OneToMany(mappedBy = "store")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    private Set<Sale> sales;
    @OneToMany(mappedBy = "store")
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        if (id != store.id) return false;
        if (storeLocation != null ? !storeLocation.equals(store.storeLocation) : store.storeLocation != null)
            return false;
        if (storeName != null ? !storeName.equals(store.storeName) : store.storeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + (storeLocation != null ? storeLocation.hashCode() : 0);
        return result;
    }
}
