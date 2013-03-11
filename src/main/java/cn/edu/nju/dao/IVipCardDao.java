package cn.edu.nju.dao;

import cn.edu.nju.bean.VipCard;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午7:29
 * To change this template use File | Settings | File Templates.
 */
public interface IVipCardDao extends IBaseDao<VipCard>{
    public void charge(VipCard vipCard,int amount);

    /**
     * 激活
     * @param vipCard
     */
    public void activate(VipCard vipCard);

    /**
     * 冻结
     * @param vipCard
     */
    public void freeze(VipCard vipCard);

    public List<Object[]> statisticRegister(int store_id);
}
