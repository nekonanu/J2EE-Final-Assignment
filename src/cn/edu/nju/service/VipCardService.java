package cn.edu.nju.service;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-1
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */
public interface VipCardService {
    public void cardCharge(int vip_card_id,double amount);
    /**
     * 判断是否过期
     * @param vip_card_id
     * @return 是否过期
     */
    public boolean isDead(int vip_card_id);

    /**
     * 设置过期与否
     * @param isDead
     */
    public void setDead(boolean isDead);


}
