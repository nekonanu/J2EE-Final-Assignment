package cn.edu.nju.controller.form;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.Size;
import org.omg.CosNaming.NamingContextPackage.NotEmptyHelper;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-5
 * Time: 上午9:04
 * To change this template use File | Settings | File Templates.
 */
public class LoginForm {
//    @NotEmpty
//    @Size(min = 5,max = 10)
    private String username;

//    @NotEmpty
//    @Size()
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
