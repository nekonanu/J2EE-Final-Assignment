package cn.edu.nju.bean;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "charge_log", schema = "", catalog = "dessert_house")
@Entity
public class ChargeLogEntity implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int holderId;

    @javax.persistence.Column(name = "holder_id")
    @Basic
    public int getHolderId() {
        return holderId;
    }

    public void setHolderId(int holderId) {
        this.holderId = holderId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChargeLogEntity that = (ChargeLogEntity) o;

        if (holderId != that.holderId) return false;
        if (id != that.id) return false;
        if (vipCardId != that.vipCardId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + holderId;
        result = 31 * result + vipCardId;
        return result;
    }
}
