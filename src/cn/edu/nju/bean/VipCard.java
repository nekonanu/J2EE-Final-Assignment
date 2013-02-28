package cn.edu.nju.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午7:12
 * To change this template use File | Settings | File Templates.
 */
public class VipCard {
    private int id;
    private int holder_id;
    private Date register_date;
    private Date deadline_date;
    private String status;
    private double remain_amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHolder_id() {
        return holder_id;
    }

    public void setHolder_id(int holder_id) {
        this.holder_id = holder_id;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public Date getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(Date deadline_date) {
        this.deadline_date = deadline_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRemain_amount() {
        return remain_amount;
    }

    public void setRemain_amount(double remain_amount) {
        this.remain_amount = remain_amount;
    }
}
