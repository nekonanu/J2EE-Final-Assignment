package cn.edu.nju.service;

import cn.edu.nju.bean.Product;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:53
 * To change this template use File | Settings | File Templates.
 */
public interface ProductService {
    public void addProduct(Product product);
    public void deleteProductByID(int id);
    public Product findByName(String name);
    public Product findByID(int id);
}
