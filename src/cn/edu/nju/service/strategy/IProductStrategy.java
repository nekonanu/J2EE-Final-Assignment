package cn.edu.nju.service.strategy;

import cn.edu.nju.bean.Product;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午5:49
 * To change this template use File | Settings | File Templates.
 */
public interface IProductStrategy {
    public Set<Product> filterHotProducts(Set<Product> allProducts);
}
