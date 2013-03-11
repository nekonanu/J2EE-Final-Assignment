package cn.edu.nju.controller.response;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-11
 * Time: 下午5:55
 * To change this template use File | Settings | File Templates.
 */
public class HotStaData {
    private double totalPercent;
    private String type;
    private List<HotProductData> datas;

    public double getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(double totalPercent) {
        this.totalPercent = totalPercent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<HotProductData> getDatas() {
        return datas;
    }

    public void setDatas(List<HotProductData> datas) {
        this.datas = datas;
    }


    public class HotProductData {
        private String productName;
        private double saleNumPercent;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getSaleNumPercent() {
            return saleNumPercent;
        }

        public void setSaleNumPercent(double saleNumPercent) {
            this.saleNumPercent = saleNumPercent;
        }
    }
}
