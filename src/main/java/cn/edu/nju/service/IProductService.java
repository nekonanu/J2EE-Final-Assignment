package cn.edu.nju.service;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.bean.Sale;
import cn.edu.nju.bean.User;
import cn.edu.nju.controller.jsonData.CustomerOrder;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:53
 * To change this template use File | Settings | File Templates.
 */
public interface IProductService{
    public void setEAGER(boolean Eager);

    public void addProduct(Product product);
    public void deleteProductByID(int id);
    public void update(Product product);
    public Product findByName(String name);
    public Product findByID(int id);

    //店员============
    /**
     * 获得当前可销售的产品
      * @return
     */
    public Set<Product> getAvailableProduct(int store_id);
    public Set<Product> getAvailableProduct(int store_id,Model model,String key);
    public void orderProduct(Product product,User user,int amount);
    public Set<ProductOrder> getProductOrders(int store_id);
    public Set<Sale> getSales(int store_id);
    public boolean customerCanAfford(List<CustomerOrder> customerOrderList,User user);

    /**
     * 分店铺分析热卖产品
     * @param store_id
     * @return
     */
    public Set<Product> getHotProducts(int store_id);
}
