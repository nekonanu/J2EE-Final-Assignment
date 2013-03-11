package cn.edu.nju.controller.response;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-11
 * Time: 下午12:45
 * To change this template use File | Settings | File Templates.
 */
public class VipStaRegisterData {
    private int year;
    private int month;
    private int day;
    private Long num;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
