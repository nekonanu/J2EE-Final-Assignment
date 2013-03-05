package cn.edu.nju.controller.response;

import cn.edu.nju.bean.VipCard;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-5
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */
public class VipCardInfo {
    private Date registerDate;
    private Date deadlineDate;
    private double remainAmount;

    public VipCardInfo(){}

    public VipCardInfo(VipCard vipCard) {
        this.registerDate = vipCard.getRegisterDate();
        this.deadlineDate = vipCard.getDeadlineDate();
        this.remainAmount = vipCard.getRemainAmount();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(double remainAmount) {
        this.remainAmount = remainAmount;
    }
}
