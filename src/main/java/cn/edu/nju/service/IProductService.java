package cn.edu.nju.service;

import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.bean.Sale;
import cn.edu.nju.bean.User;
import cn.edu.nju.controller.jsonData.CustomerOrderItem;
import cn.edu.nju.controller.response.HotStaData;
import cn.edu.nju.controller.response.OrderSaleStaData;
import cn.edu.nju.controller.response.OrderSaleTypePieData;
import org.springframework.ui.Model;

import java.util.Date;
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
    public void saleProduct(int orderID);
    public Set<ProductOrder> getUncheckedProductOrders(int store_id);
    public Set<Sale> getSales(int store_id);
    public boolean customerCanAfford(List<CustomerOrderItem> customerOrderItemList,User user);

    public List<Product> searchProduct(String searchText,int store_id);

    /**
     * 分店铺分析热卖产品
     * @param store_id
     * @return
     */
    public List<HotStaData> getHotProducts(int store_id);

    public List<String> getAllProductType();

    public List<OrderSaleStaData> getOrderStaData(Date begin,Date end,int storeID);

    public List<OrderSaleTypePieData> getOrderTypePercent(Date begin,Date end,int storeID);

    public List<OrderSaleStaData> getSaleStaData(Date begin,Date end,int storeID);

    public List<OrderSaleTypePieData> getSaleTypePercent(Date begin,Date end,int storeID);


}
