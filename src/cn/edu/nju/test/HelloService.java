package cn.edu.nju.test;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
public class HelloService {
    private String str;

    public void sayHello(){
        System.out.println(str);
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
