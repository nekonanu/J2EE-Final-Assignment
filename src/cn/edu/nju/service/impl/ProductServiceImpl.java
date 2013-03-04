package cn.edu.nju.service.impl;

import cn.edu.nju.bean.ProductEntity;
import cn.edu.nju.bean.ProductOrderEntity;
import cn.edu.nju.dao.OrderDao;
import cn.edu.nju.dao.ProductDao;
import cn.edu.nju.service.ProductService;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
public class ProductServiceImpl implements ProductService{
    private ProductDao productDao;
    private OrderDao orderDao;

    public ProductDao getProductDao() {
        return productDao;
    }

    @Override
    public void addProduct(ProductEntity product) {
        productDao.addProduct(product);
    }

    @Override
    public void deleteProductByID(int id) {
        productDao.deleteProductByID(id);
    }

    @Override
    public ProductEntity findByName(String name) {
        return productDao.findByName(name);
    }

    @Override
    public ProductEntity findByID(int id) {
        return productDao.findByID(id);
    }

    @Override
    public List<ProductEntity> getAvailableProduct() {
        return productDao.getAllAvailableProduct();
    }

    @Override
    public void sellProduct(int product_id, int user_id, int amount) {
        ProductOrderEntity order=new ProductOrderEntity();
        order.setCustomerId(user_id);
        order.setProductId(product_id);
        order.setOrderNum(amount);
        order.setOrderDate(new Date());
        orderDao.addOrder(order);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
