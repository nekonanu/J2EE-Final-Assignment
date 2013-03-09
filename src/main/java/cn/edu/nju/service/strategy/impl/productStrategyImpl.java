package cn.edu.nju.service.strategy.impl;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.bean.Sale;
import cn.edu.nju.controller.response.HotStaInfo;
import cn.edu.nju.service.strategy.IProductStrategy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午5:50
 * To change this template use File | Settings | File Templates.
 */
@Component
public class productStrategyImpl implements IProductStrategy{
    private final int HOT_PRODUCT_NUM=100;//热门商品售卖数

    @Override
    public Set<HotStaInfo> filterHotProducts(Set<Product> allProducts) {
//        Set<Product> hotProducts=new HashSet<Product>();
//        for (Product product:allProducts){
//            if (countSaleProduct(product)>=HOT_PRODUCT_NUM){
//                hotProducts.add(product);
//            }
//        }
        Set<HotStaInfo> infos=new HashSet<HotStaInfo>();
        for(Product product:allProducts){
            int order_num=getOrderNum(product);
            int sale_num=getSaleNum(product);
            HotStaInfo info=new HotStaInfo();
            info.setOrder_num(order_num);
            info.setSale_num(sale_num);
            info.setProduct(product);
            infos.add(info);
        }

        return infos;
    }

    private int getOrderNum(Product product){
        int order_num=0;
        Set<ProductOrder> orders=product.getProductOrder();
        for(ProductOrder order:orders){
            order_num+=order.getOrderNum();
        }
        return order_num;
    }

    private int getSaleNum(Product product){
        int saleNum=0;
        Set<Sale> sales=product.getSales();
        for (Sale sale:sales){
            saleNum+=sale.getSaleNum();
        }
        return saleNum;
    }

    private int countSaleProduct(Product product){
        Set<Sale> sales=product.getSales();
        int count=0;
        for (Sale sale:sales){
            count+=sale.getSaleNum();
        }
        return count;
    }
}
