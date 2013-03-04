package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.VipCard;
import cn.edu.nju.dao.IVipCardDao;
import org.springframework.stereotype.Repository;


/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class VipCardDaoImpl extends BaseDaoSupport<VipCard> implements IVipCardDao {
    @Override
    public void charge(VipCard vipCard, int amount) {
        vipCard.setRemainAmount(vipCard.getRemainAmount()+amount);
        getSession().update(vipCard);
    }

    @Override
    public void activate(VipCard vipCard) {
        vipCard.setStatus("activate");
        getSession().update(vipCard);
    }

    @Override
    public void freeze(VipCard vipCard) {
        vipCard.setStatus("freeze");
    }
}
