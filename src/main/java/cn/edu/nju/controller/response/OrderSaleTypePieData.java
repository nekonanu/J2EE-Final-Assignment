package cn.edu.nju.controller.response;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-11
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class OrderSaleTypePieData {
    private String type;
    private double percent;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
