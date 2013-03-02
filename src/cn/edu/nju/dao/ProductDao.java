package cn.edu.nju.dao;

import cn.edu.nju.bean.Product;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
public interface ProductDao {
    public void addProduct(Product product);
    public void deleteProductByID(int id);
    public Product findByName(String name);
    public Product findByID(int id);
    public List<Product> getAllAvailableProduct();
}
