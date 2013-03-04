package cn.edu.nju.service;

import cn.edu.nju.bean.User;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-1
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */
public interface VipCardService {
    public void cardCharge(User user,int amount);
    /**
     * 判断是否过期
     * @param user
     * @return 是否过期
     */
    public boolean isFreeze(User user);

    /**
     * 设置过期与否
     * @param user,isFreeze
     */
    public void setFreeze(User user,boolean isFreeze);


}
