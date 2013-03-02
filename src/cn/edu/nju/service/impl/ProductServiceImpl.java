package cn.edu.nju.service.impl;

import cn.edu.nju.bean.Order;
import cn.edu.nju.bean.Product;
import cn.edu.nju.dao.OrderDao;
import cn.edu.nju.dao.ProductDao;
import cn.edu.nju.service.ProductService;

import java.util.Calendar;
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

    @Override
    public List<Product> getAvailableProduct() {
        return productDao.getAllAvailableProduct();
    }

    @Override
    public void sellProduct(int product_id, int user_id, int amount) {
        Order order=new Order();
        order.setCustomer_id(user_id);
        order.setProduct_id(product_id);
        order.setOrder_num(amount);
        order.setOrder_date(new Date());
        orderDao.addOrder(order);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
