package cn.edu.nju.controller.validation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-6
 * Time: 下午7:22
 * To change this template use File | Settings | File Templates.
 */
public class CustomerSignUpForm {

    @NotNull(message = "用户名不能为空")
    @Size(min = 2,max = 8,message = "用户名不能少于2个字符和超过8个字符")

    private String userName;
    @NotNull(message = "密码不能为空")

    private String password;
    @NotNull(message = "请再次输入密码")
    private String passwordConfirm;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @AssertTrue(message = "两次密码输入不匹配")
    public boolean confirmPassword(){
        return password.equals(passwordConfirm);
    }
}
