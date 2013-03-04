package cn.edu.nju.service.impl;

import cn.edu.nju.bean.ProductEntity;
import cn.edu.nju.bean.ProductOrderEntity;
import cn.edu.nju.bean.UserEntity;
import cn.edu.nju.dao.IOrderDao;
import cn.edu.nju.dao.IProductDao;
import cn.edu.nju.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    private IProductDao IProductDao;
    @Autowired
    private IOrderDao IOrderDao;

    public IProductDao getIProductDao() {
        return IProductDao;
    }

    @Override
    public void addProduct(ProductEntity product) {
        IProductDao.save(product);
    }

    @Override
    public void deleteProductByID(int id) {
        IProductDao.deleteById(id);
    }

    @Override
    public ProductEntity findByName(String name) {
        return IProductDao.findByName(name);
    }

    @Override
    public ProductEntity findByID(int id) {
        return IProductDao.findById(id);
    }

    @Override
    public List<ProductEntity> getAvailableProduct() {
        return IProductDao.getAllAvailableProduct();
    }

    @Override
    public void sellProduct(ProductEntity productEntity, UserEntity userEntity, int amount) {
        ProductOrderEntity order=new ProductOrderEntity();
        order.setUserEntity(userEntity);
        order.setProductEntity(productEntity);
        order.setOrderNum(amount);
        order.setOrderDate(new Date());
        IOrderDao.save(order);
    }

//    @Override
//    public void sellProduct(int product_id, int user_id, int amount) {
//        ProductOrderEntity order=new ProductOrderEntity();
//        order.setCustomerId(user_id);
//        order.setProductId(product_id);
//        order.setOrderNum(amount);
//        order.setOrderDate(new Date());
//        IOrderDao.save(order);
//    }

    public void setIProductDao(IProductDao IProductDao) {
        this.IProductDao = IProductDao;
    }

    public void setIOrderDao(IOrderDao IOrderDao) {
        this.IOrderDao = IOrderDao;
    }
}
