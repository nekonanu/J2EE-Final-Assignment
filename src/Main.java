import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.User;
import cn.edu.nju.service.IProductService;
import cn.edu.nju.service.IUserService;
import cn.edu.nju.test.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String args[]){
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-config.xml");
        HelloService helloService= (HelloService) context.getBean("helloService");
        helloService.sayHello();

        Product product=new Product();
        product.setPrice(32.0);
        product.setProductName("喜之郎");
        product.setRemainNum(100);

        IProductService service= (IProductService) context.getBean("productService");
        IUserService IUserService = (IUserService) context.getBean("userService");
//        Product productEntity=service.findByID(4);
        User user = IUserService.findUserByID(1);
        System.out.println(user.getVipCard().getStatus());
//        service.sellProduct(productEntity, user,100);

//        service.addProduct(product);
//        service.sellProduct(4,1,10);

//        service.addProduct(product);
    }
}
