package cn.edu.nju.service.impl;

import cn.edu.nju.bean.*;
import cn.edu.nju.controller.jsonData.CustomerOrder;
import cn.edu.nju.dao.IOrderDao;
import cn.edu.nju.dao.IProductDao;
import cn.edu.nju.dao.IStoreDao;
import cn.edu.nju.service.IProductService;
import cn.edu.nju.service.strategy.IProductStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IStoreDao storeDao;
    @Autowired
    private IProductStrategy productStrategy;

    private boolean eager;

    public IProductDao getProductDao() {
        return productDao;
    }

    @Override
    public void setEAGER(boolean Eager) {
        this.eager=Eager;
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
    public void update(Product product) {
        productDao.update(product);
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
    public Set<Product> getAvailableProduct(int store_id, Model model, String key) {
        Set<Product> products= storeDao.findById(store_id).getProducts();
        model.addAttribute(key,products);
        return products;
    }

    @Override
    public void orderProduct(Product product, User user, int amount) {
        ProductOrder order=new ProductOrder();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderNum(amount);
        order.setOrderDate(new Date());
        order.setStore(user.getStore());
        orderDao.save(order);
    }

    @Override
    public Set<ProductOrder> getProductOrders(int store_id) {
        return storeDao.findById(store_id).getProductOrders();
    }

    @Override
    public Set<Sale> getSales(int store_id) {
        return storeDao.findById(store_id).getSales();
    }

    @Override
    public boolean customerCanAfford(List<CustomerOrder> customerOrderList,User user) {
        double pay=0;
        for(CustomerOrder order:customerOrderList){
            Product product=findByID(order.getProduct_id());
            pay+=product.getPrice()*order.getProduct_num();
        }
        if (user.getVipCard().getRemainAmount()>=pay)
            return true;
        else
            return false;
    }

    @Override
    public Set<Product> getHotProducts(int store_id) {
        return productStrategy.filterHotProducts(storeDao.findById(store_id).getProducts());
    }



    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
