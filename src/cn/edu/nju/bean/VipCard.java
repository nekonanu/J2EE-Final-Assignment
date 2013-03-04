package cn.edu.nju.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "vip_card", schema = "", catalog = "dessert_house")
@Entity
public class VipCard implements Serializable {
    public static final String FREEZE="freeze";
    public static final String ACTIVATE="activate";

    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    private int holderId;
//
//    @javax.persistence.Column(name = "holder_id")
//    @Basic
//    public int getHolderId() {
//        return holderId;
//    }
//
//    public void setHolderId(int holderId) {
//        this.holderId = holderId;
//    }
    private User user;

    @OneToOne(mappedBy = "vipCard")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Date registerDate;

    @javax.persistence.Column(name = "register_date")
    @Basic
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    private Date deadlineDate;

    @javax.persistence.Column(name = "deadline_date")
    @Basic
    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    private String status;

    @javax.persistence.Column(name = "status")
    @Basic
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private double remainAmount;

    @javax.persistence.Column(name = "remain_amount")
    @Basic
    public double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(double remainAmount) {
        this.remainAmount = remainAmount;
    }

    private Set<ChargeLog> chargeLogs;

    @OneToMany(mappedBy = "vipCard")
    public Set<ChargeLog> getChargeLogs() {
        return chargeLogs;
    }

    public void setChargeLogs(Set<ChargeLog> chargeLogs) {
        this.chargeLogs = chargeLogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VipCard that = (VipCard) o;

        if (user != that.user) return false;
        if (id != that.id) return false;
        if (Double.compare(that.remainAmount, remainAmount) != 0) return false;
        if (deadlineDate != null ? !deadlineDate.equals(that.deadlineDate) : that.deadlineDate != null) return false;
        if (registerDate != null ? !registerDate.equals(that.registerDate) : that.registerDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + (deadlineDate != null ? deadlineDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        temp = remainAmount != +0.0d ? Double.doubleToLongBits(remainAmount) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
