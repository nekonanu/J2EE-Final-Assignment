package cn.edu.nju.service.impl;

import cn.edu.nju.bean.*;
import cn.edu.nju.controller.jsonData.CustomerOrderItem;
import cn.edu.nju.controller.response.HotStaData;
import cn.edu.nju.controller.response.OrderSaleStaData;
import cn.edu.nju.controller.response.OrderSaleTypePieData;
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
        product.setRemainNum(product.getRemainNum()-amount);
        ProductOrder order=new ProductOrder();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderNum(amount);
        order.setOrderDate(new Date());
        order.setStore(user.getStore());
        order.setOrderCheck("false");
        order.setPay(product.getPrice()*user.getVipCard().getCutoff()*amount);
        orderDao.save(order);
        productDao.update(product);
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
        sale.setPay(order.getPay());
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
    public boolean customerCanAfford(List<CustomerOrderItem> customerOrderItemList,User user) {
        double pay=0;
        for(CustomerOrderItem order: customerOrderItemList){
            Product product=findByID(order.getProduct_id());
            pay+=product.getPrice()*order.getProduct_num();
        }
        if ((user.getVipCard().getRemainAmount()*user.getVipCard().getCutoff())>=pay)
            return true;
        else
            return false;
    }

    @Override
    public List<Product> searchProduct(String searchText,int store_id) {
        return productDao.findByLike(searchText,store_id);
    }

    @Override
    public List<HotStaData> getHotProducts(int store_id) {
        Store store=storeDao.findById(store_id);
        Set<Sale> sales=store.getSales();
        List<HotStaData> hotStaDatas=new ArrayList<HotStaData>();
        return null;
    }

    private List<HotStaData.HotProductData> getHotProductData(Set<Sale> sales,String type){
        return null;
    }

    @Override
    public List<String> getAllProductType() {
        return productDao.getAllProductType();
    }

    @Override
    public List<OrderSaleStaData> getOrderStaData(Date begin, Date end,int storeID){
        List<ProductOrder> orders=orderDao.findBetweenDate(begin,end,storeID);
        Map<Date,Double> map=new HashMap();
        List<OrderSaleStaData> datas=new ArrayList<OrderSaleStaData>();
        for (ProductOrder order:orders){
            if (map.get(order.getOrderDate())!=null){
                map.put(order.getOrderDate(),map.get(order.getOrderDate())+order.getPay());
            }else{
                map.put(order.getOrderDate(),order.getPay());
            }
        }
        Set set = map.entrySet();
        for (Iterator iterator = set.iterator();iterator.hasNext();){
            Map.Entry entry= (Map.Entry) iterator.next();
            Date date=((Map.Entry<Date, Double>) entry).getKey();
            Double totalPay= ((Map.Entry<Date, Double>) entry).getValue();

            OrderSaleStaData staData=new OrderSaleStaData();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            staData.setDate(date);
            staData.setPay(totalPay);
            staData.setYear(calendar.get(Calendar.YEAR));
            staData.setMonth(calendar.get(Calendar.MONTH)+1);
            staData.setDay(calendar.get(Calendar.DATE));
            datas.add(staData);
        }
        Collections.sort(datas,new OrderSaleStaData());
        return datas;
    }

    @Override
    public List<OrderSaleTypePieData> getOrderTypePercent(Date begin, Date end, int storeID) {
        List<ProductOrder> orders=orderDao.findBetweenDate(begin,end,storeID);
        int totalNum=0;
        for (ProductOrder order:orders){
            totalNum+=order.getOrderNum();
        }
        Map<String,Integer> map=new HashMap<String, Integer>();
        List<OrderSaleTypePieData> datas=new ArrayList<OrderSaleTypePieData>();
        for (ProductOrder order:orders){
            if (map.get(order.getProduct().getProductType())!=null){
                map.put(order.getProduct().getProductType(),map.get(order.getProduct().getProductType())+order.getOrderNum());
            }else{
                map.put(order.getProduct().getProductType(),order.getOrderNum());
            }
        }
        Set set = map.entrySet();
        for (Iterator iterator = set.iterator();iterator.hasNext();){
            Map.Entry entry= (Map.Entry) iterator.next();
            String type=((Map.Entry<String,Integer>) entry).getKey();
            Integer num= ((Map.Entry<String,Integer>) entry).getValue();
            OrderSaleTypePieData data=new OrderSaleTypePieData();
            data.setType(type);
            double percent=(double)num/(double)totalNum;
            data.setPercent(percent);
            datas.add(data);
        }
        return datas;
    }

    @Override
    public List<OrderSaleStaData> getSaleStaData(Date begin, Date end, int storeID) {
        List<Sale> orders=saleDao.findBetweenDate(begin,end,storeID);
        Map<Date,Double> map=new HashMap();
        List<OrderSaleStaData> datas=new ArrayList<OrderSaleStaData>();
        for (Sale order:orders){
            if (map.get(order.getSaleDate())!=null){
                map.put(order.getSaleDate(),map.get(order.getSaleDate())+order.getPay());
            }else{
                map.put(order.getSaleDate(),order.getPay());
            }
        }
        Set set = map.entrySet();
        for (Iterator iterator = set.iterator();iterator.hasNext();){
            Map.Entry entry= (Map.Entry) iterator.next();
            Date date=((Map.Entry<Date, Double>) entry).getKey();
            Double totalPay= ((Map.Entry<Date, Double>) entry).getValue();

            OrderSaleStaData staData=new OrderSaleStaData();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            staData.setDate(date);
            staData.setPay(totalPay);
            staData.setYear(calendar.get(Calendar.YEAR));
            staData.setMonth(calendar.get(Calendar.MONTH)+1);
            staData.setDay(calendar.get(Calendar.DATE));
            datas.add(staData);
        }
        Collections.sort(datas,new OrderSaleStaData());
        return datas;
    }

    @Override
    public List<OrderSaleTypePieData> getSaleTypePercent(Date begin, Date end, int storeID) {
        List<Sale> orders=saleDao.findBetweenDate(begin,end,storeID);
        int totalNum=0;
        for (Sale order:orders){
            totalNum+=order.getSaleNum();
        }
        Map<String,Integer> map=new HashMap<String, Integer>();
        List<OrderSaleTypePieData> datas=new ArrayList<OrderSaleTypePieData>();
        for (Sale order:orders){
            if (map.get(order.getProduct().getProductType())!=null){
                map.put(order.getProduct().getProductType(),map.get(order.getProduct().getProductType())+order.getSaleNum());
            }else{
                map.put(order.getProduct().getProductType(),order.getSaleNum());
            }
        }
        Set set = map.entrySet();
        for (Iterator iterator = set.iterator();iterator.hasNext();){
            Map.Entry entry= (Map.Entry) iterator.next();
            String type=((Map.Entry<String,Integer>) entry).getKey();
            Integer num= ((Map.Entry<String,Integer>) entry).getValue();
            OrderSaleTypePieData data=new OrderSaleTypePieData();
            data.setType(type);
            double percent=(double)num/(double)totalNum;
            data.setPercent(percent);
            datas.add(data);
        }
        return datas;
    }


    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
