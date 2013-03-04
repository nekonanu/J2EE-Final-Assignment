package cn.edu.nju.service.strategy.impl;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.Sale;
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
    public Set<Product> filterHotProducts(Set<Product> allProducts) {
        Set<Product> hotProducts=new HashSet<Product>();
        for (Product product:allProducts){
            if (countSaleProduct(product)>=HOT_PRODUCT_NUM){
                hotProducts.add(product);
            }
        }
        return hotProducts;
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
