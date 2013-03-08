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
@javax.persistence.Table(name = "user", schema = "", catalog = "dessert_house")
@Entity
public class User implements Serializable {
    public static final String CUSTOMER="CUSTOMER";
    public static final String CASHIER="CASHIER";
    public static final String MANAGER="MANAGER";
    public static final String SYSTEM_MANAGER="SYSTEM_MANAGER";

    public User(){}
    public User(String userName, String password, VipCard vipCard, String type, Store store) {
        this.userName = userName;
        this.password = password;
        this.vipCard = vipCard;
        this.type = type;
        this.store = store;
    }

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

    private Set<ProductOrder> productOrderEntities;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    public Set<ProductOrder> getProductOrderEntities() {
        return productOrderEntities;
    }

    public void setProductOrderEntities(Set<ProductOrder> productOrderEntities) {
        this.productOrderEntities = productOrderEntities;
    }

    private Set<Sale> sales;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    private String userName;

    @javax.persistence.Column(name = "user_name")
    @Basic
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password;

    @javax.persistence.Column(name = "password")
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;

    @javax.persistence.Column(name = "email")
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private VipCard vipCard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vip_card_id")
    public VipCard getVipCard() {
        return vipCard;
    }

    public void setVipCard(VipCard vipCard) {
        this.vipCard = vipCard;
    }

    private int age;

    @javax.persistence.Column(name = "age")
    @Basic
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int sex;

    @javax.persistence.Column(name = "sex")
    @Basic
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    private String address;

    @javax.persistence.Column(name = "address")
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String type;

    @Column(name = "type")
    @Basic
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private Set<ChargeLog> chargeLogs;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    public Set<ChargeLog> getChargeLogs() {
        return chargeLogs;
    }

    public void setChargeLogs(Set<ChargeLog> chargeLogs) {
        this.chargeLogs = chargeLogs;
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

        User that = (User) o;

        if (age != that.age) return false;
        if (id != that.id) return false;
        if (sex != that.sex) return false;
        if (type != that.type) return false;
        if (vipCard != that.vipCard) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (vipCard==null?0:vipCard.getId());
        result = 31 * result + age;
        result = 31 * result + sex;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
