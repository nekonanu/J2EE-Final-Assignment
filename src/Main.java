import cn.edu.nju.bean.ProductEntity;
import cn.edu.nju.bean.UserEntity;
import cn.edu.nju.service.ProductService;
import cn.edu.nju.service.UserService;
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

        ProductEntity product=new ProductEntity();
        product.setPrice(32.0);
        product.setProductName("喜之郎");
        product.setRemainNum(100);

        ProductService service= (ProductService) context.getBean("productService");
        UserService userService= (UserService) context.getBean("userService");
        ProductEntity productEntity=service.findByID(4);
        UserEntity userEntity= userService.findUserByID(1);
        service.sellProduct(productEntity,userEntity,100);

//        service.addProduct(product);
//        service.sellProduct(4,1,10);

//        service.addProduct(product);
    }
}
