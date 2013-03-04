package cn.edu.nju.bean;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "charge_log", schema = "", catalog = "dessert_house")
@Entity
public class ChargeLog {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private User user;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "holder_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private VipCard vipCard;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "vip_card_id")
    public VipCard getVipCard() {
        return vipCard;
    }

    public void setVipCard(VipCard vipCard) {
        this.vipCard = vipCard;
    }

    private double chargeAmount;

    @javax.persistence.Column(name = "charge_amount")
    @Basic
    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    private Date chargeDate;

    @javax.persistence.Column(name = "charge_date")
    @Basic
    public Date getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(Date chargeDate) {
        this.chargeDate = chargeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChargeLog chargeLog = (ChargeLog) o;

        if (Double.compare(chargeLog.chargeAmount, chargeAmount) != 0) return false;
        if (user != chargeLog.user) return false;
        if (id != chargeLog.id) return false;
        if (vipCard != chargeLog.vipCard) return false;
        if (chargeDate != null ? !chargeDate.equals(chargeLog.chargeDate) : chargeLog.chargeDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + vipCard.hashCode();
        temp = chargeAmount != +0.0d ? Double.doubleToLongBits(chargeAmount) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (chargeDate != null ? chargeDate.hashCode() : 0);
        return result;
    }
}
