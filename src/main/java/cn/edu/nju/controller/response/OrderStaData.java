package cn.edu.nju.controller.response;

import java.util.Comparator;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-11
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class OrderStaData implements Comparator{
    private double pay;
    private int year;
    private int month;
    private int day;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

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

    @Override
    public int compare(Object o1, Object o2) {
        Date date1=((OrderStaData)o1).getDate();
        Date date2=((OrderStaData)o2).getDate();
        return date1.compareTo(date2);
    }
}
