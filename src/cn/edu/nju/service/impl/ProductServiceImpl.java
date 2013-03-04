package cn.edu.nju.service.impl;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.bean.User;
import cn.edu.nju.dao.IOrderDao;
import cn.edu.nju.dao.IProductDao;
import cn.edu.nju.dao.IStoreDao;
import cn.edu.nju.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IStoreDao storeDao;

    public IProductDao getProductDao() {
        return productDao;
    }

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteProductByID(int id) {
        productDao.deleteById(id);
    }

    @Override
    public Product findByName(String name) {
        return productDao.findByName(name);
    }

    @Override
    public Product findByID(int id) {
        return productDao.findById(id);
    }

    @Override
    public Set<Product> getAvailableProduct(int store_id) {
        return storeDao.findById(store_id).getProducts();
    }

    @Override
    public void sellProduct(Product product, User user, int amount) {
        ProductOrder order=new ProductOrder();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderNum(amount);
        order.setOrderDate(new Date());
        orderDao.save(order);
    }

    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
