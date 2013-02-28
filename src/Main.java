import cn.edu.nju.bean.Product;
import cn.edu.nju.bean.User;
import cn.edu.nju.dao.UserDao;
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

//        Product product=new Product();
//        product.setId(1);
//        product.setPrice(32.0);
//        product.setProduct_name("喜之郎");
//        product.setRemain_num(100);

        ProductService service= (ProductService) context.getBean("productService");
        Product product=service.findByID(3);
        System.out.println(product.getProduct_name());

        UserService userService= (UserService) context.getBean("userService");
        User user=userService.findUserByID(1);
        System.out.println(user.getUser_name());
    }
}
