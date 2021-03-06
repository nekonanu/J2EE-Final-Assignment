package cn.edu.nju.test;

import cn.edu.nju.bean.*;
import cn.edu.nju.controller.response.OrderSaleTypePieData;
import cn.edu.nju.dao.IOrderDao;
import cn.edu.nju.dao.ISaleDao;
import cn.edu.nju.dao.impl.OrderDaoImpl;
import cn.edu.nju.dao.impl.SaleDaoImpl;
import cn.edu.nju.service.IProductService;
import cn.edu.nju.service.IStoreService;
import cn.edu.nju.service.IUserService;
import cn.edu.nju.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static IProductService productService;
    public static IUserService userService;
    public static IStoreService storeService;
    public static IOrderDao orderDao;
    public static ISaleDao saleDao;

    static {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/spring-config.xml");
        productService = (IProductService) context.getBean("productService");
        userService = (IUserService) context.getBean("userService");
        storeService= (IStoreService) context.getBean("storeService");
        orderDao=context.getBean(OrderDaoImpl.class);
        saleDao=context.getBean(SaleDaoImpl.class);
        HibernateUtil.setSession_factory((SessionFactory) context.getBean("mySessionFactory"));
    }

    public static void main(String args[]){
        StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
        System.out.println(encoder.matches("123","df1937975095340dc353fffbbeeaf8f8b8ff30c65d4ff0b220da5764dbd5ca3f1b24b56a39ca09f1"));
//        List<Product> products = productService.searchProduct("娃娃",1);

//        Calendar calendar=Calendar.getInstance();
//        calendar.add(Calendar.MONTH,-1);
//        Date begin=calendar.getTime();
//        calendar=Calendar.getInstance();
//        Date end=calendar.getTime();
//        List<OrderSaleTypePieData> list=productService.getOrderTypePercent(begin, end, 1);
//        productService.saleProduct(1);
//        Session session=HibernateUtil.currentSession();
//        ProductOrder order= (ProductOrder) session.get(ProductOrder.class,1);
//        Sale sale=new Sale();
//        sale.setStore(order.getStore());
//        sale.setUser(order.getUser());
//        sale.setProduct(order.getProduct());
//        sale.setSaleDate(new Date());
//        sale.setSaleNum(order.getOrderNum());
//        session.save(sale);


//        orderDao.deleteById(1);
//        store.setId(2);
//        storeService.deleteStore(1);
//        productService.deleteProductByID(4);
//        System.out.println("=======");
//        User user=userService.findUserByID(9);
//        System.out.println("=======");
//        productService.orderProduct(product,user,1);
//        testAddStore("2");
//        testAddProduct(1);
//        testAddOrder(4,9,1);
//            testFindProduct();
//        Session session=HibernateUtil.currentSession();
//        Transaction tx=session.beginTransaction();
//        Product product= (Product) session.get(Product.class,4);
//        Set<ProductOrder> orders=product.getProductOrder();
//        System.out.println(orders.size());
//        Query query=session.createQuery("from User u where u.userName='nekosama'");
//        List list=query.list();
//        tx.commit();
//        HibernateUtil.closeSession();
//        System.out.println(list.size());
//        System.out.println("get "+productOrders.size());

//        Product productEntity=productService.findByID(4);

//        System.out.println(user.getVipCard().getStatus());
//        productService.sellProduct(productEntity, user,100);

//        productService.addProduct(product);
//        productService.sellProduct(4,1,10);

//        productService.addProduct(product);
    }

    private static void testFindProduct(){
        Session session=HibernateUtil.currentSession();
        Product product= (Product) session.get(Product.class,4);
    }

    private static void testAddUser(){
        User addUser=new User();
        addUser.setUserName("nekosama");
        addUser.setPassword("123");
        addUser.setType(User.CUSTOMER);

        VipCard vipCard=new VipCard();
        vipCard.setRegisterDate(new Date());
        vipCard.setDeadlineDate(new Date(System.currentTimeMillis()+365*24*3600*1000));
        vipCard.setStatus("freeze");
        vipCard.setRemainAmount(0);

        addUser.setVipCard(vipCard);
        vipCard.setHolder(addUser);
        addUser.setStore(storeService.findByID(1));//TODO here make the storeID fixed

        userService.addUser(addUser);
    }


    private static void testAddStore(String i){
        Store store=new Store();
        store.setStoreName("S"+i);
        store.setStoreLocation("L"+i);
        storeService.addStore(store);
    }

    public static void testAddProduct(int i){
        Product product=new Product();
        product.setProductName("PN"+i);
        product.setPrice(1);
        product.setRemainNum(1);
        Store store=storeService.findByID(1);
        product.setStore(store);
        productService.addProduct(product);
    }

    public static void testAddSale(int product_id,int customer_id,int store_id){
        Sale sale=new Sale();
        sale.setSaleNum(1);
        sale.setSaleDate(new Date());
        sale.setProduct(productService.findByID(product_id));
        sale.setUser(userService.findUserByID(customer_id));
        sale.setStore(storeService.findByID(store_id));

    }

//    public static void testAddOrder(int product_id,int customer_id,int store_id){
//        ProductOrder order=new ProductOrder();
//        order.setOrderDate(new Date());
//        order.setOrderNum(1);
//        order.setUser(userService.findUserByID(customer_id));
//        order.setProduct(productService.findByID(product_id));
//        order.setStore(storeService.findByID(store_id));
//
//        Session session=HibernateUtil.currentSession();
//        Transaction tx=session.beginTransaction();
//        session.save(order);
//        tx.commit();
//        HibernateUtil.closeSession();
////        orderDao.save(order);
//    }

    public static void addChargeLog(){

    }
}
