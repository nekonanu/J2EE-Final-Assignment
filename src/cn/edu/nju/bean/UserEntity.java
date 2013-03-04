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
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "user", schema = "", catalog = "dessert_house")
@Entity
public class UserEntity implements Serializable {
    private Set<ProductOrderEntity> productOrderEntities;

    @OneToMany(mappedBy = "userEntity")
    public Set<ProductOrderEntity> getProductOrderEntities() {
        return productOrderEntities;
    }

    public void setProductOrderEntities(Set<ProductOrderEntity> productOrderEntities) {
        this.productOrderEntities = productOrderEntities;
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

    private int vipCardId;

    @javax.persistence.Column(name = "vip_card_id")
    @Basic
    public int getVipCardId() {
        return vipCardId;
    }

    public void setVipCardId(int vipCardId) {
        this.vipCardId = vipCardId;
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

    private int type;

    @javax.persistence.Column(name = "type")
    @Basic
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (age != that.age) return false;
        if (id != that.id) return false;
        if (sex != that.sex) return false;
        if (type != that.type) return false;
        if (vipCardId != that.vipCardId) return false;
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
        result = 31 * result + vipCardId;
        result = 31 * result + age;
        result = 31 * result + sex;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + type;
        return result;
    }
}
