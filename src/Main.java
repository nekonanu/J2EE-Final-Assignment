import cn.edu.nju.bean.Product;
import cn.edu.nju.service.ProductService;
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
        product.setId(1);
        product.setPrice(32.0);
        product.setProduct_name("喜之郎");
        product.setRemainal_num(100);

        ProductService service= (ProductService) context.getBean("productService");
        service.addProduct(product);

    }
}
