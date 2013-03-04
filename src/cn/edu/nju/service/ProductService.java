package cn.edu.nju.service;

import cn.edu.nju.bean.ProductEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:53
 * To change this template use File | Settings | File Templates.
 */
public interface ProductService {
    public void addProduct(ProductEntity product);
    public void deleteProductByID(int id);
    public ProductEntity findByName(String name);
    public ProductEntity findByID(int id);

    //店员============
    /**
     * 获得当前可销售的产品
      * @return
     */
    public List<ProductEntity> getAvailableProduct();
    public void sellProduct(int product_id,int user_id,int amount);


}
