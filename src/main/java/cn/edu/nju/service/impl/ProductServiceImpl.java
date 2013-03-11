package cn.edu.nju.service.impl;

import cn.edu.nju.bean.*;
import cn.edu.nju.controller.jsonData.CustomerOrder;
import cn.edu.nju.controller.response.HotStaInfo;
import cn.edu.nju.dao.IOrderDao;
import cn.edu.nju.dao.IProductDao;
import cn.edu.nju.dao.ISaleDao;
import cn.edu.nju.dao.IStoreDao;
import cn.edu.nju.service.IProductService;
import cn.edu.nju.service.strategy.IProductStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

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
    @Autowired
    private ISaleDao saleDao;

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
        order.setOrderCheck("false");
        orderDao.save(order);
    }

    @Override
    public void saleProduct(int orderID) {
        ProductOrder order=orderDao.findById(orderID);
        order.setOrderCheck("true");
        Sale sale=new Sale();
        sale.setStore(order.getStore());
        sale.setUser(order.getUser());
        sale.setProduct(order.getProduct());
        sale.setSaleDate(new Date());
        sale.setSaleNum(order.getOrderNum());
        saleDao.save(sale);
        orderDao.update(order);
    }

    @Override
    public Set<ProductOrder> getUncheckedProductOrders(int store_id) {
        Set<ProductOrder> orders= storeDao.findById(store_id).getProductOrders();
        Set<ProductOrder> result=new HashSet<ProductOrder>();
        Iterator<ProductOrder> iterator=orders.iterator();
        while (iterator.hasNext()){
            ProductOrder tmp=iterator.next();
            if (tmp.getOrderCheck().equals("false")){
                result.add(tmp);
            }
        }
        return result;
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
        if ((user.getVipCard().getRemainAmount()*user.getVipCard().getCutoff())>=pay)
            return true;
        else
            return false;
    }

    @Override
    public Set<HotStaInfo> getHotProducts(int store_id) {
        return productStrategy.filterHotProducts(storeDao.findById(store_id).getProducts());
    }



    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
