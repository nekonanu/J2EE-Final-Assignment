package cn.edu.nju.service.impl;

import cn.edu.nju.bean.Product;
import cn.edu.nju.dao.ProductDao;
import cn.edu.nju.service.ProductService;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
public class ProductServiceImpl implements ProductService{
    private ProductDao productDao;

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public void deleteProductByID(int id) {
        productDao.deleteProductByID(id);
    }

    @Override
    public Product findByName(String name) {
        return productDao.findByName(name);
    }

    @Override
    public Product findByID(int id) {
        return productDao.findByID(id);
    }
}
