package cn.edu.nju.service.impl;

import cn.edu.nju.bean.User;
import cn.edu.nju.bean.VipCard;
import cn.edu.nju.dao.IUserDao;
import cn.edu.nju.dao.IVipCardDao;
import cn.edu.nju.service.VipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
@Service
public class VipCardServiceImpl implements VipCardService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IVipCardDao vipCardDao;

    @Override
    public void cardCharge(User user, int amount) {
        VipCard vipCard=user.getVipCard();
        vipCardDao.charge(vipCard,amount);
    }

    @Override
    public boolean isFreeze(User user) {
        String status = user.getVipCard().getStatus();
        return status.equals(VipCard.FREEZE);
    }

    @Override
    public void setFreeze(User user, boolean isFreeze) {
        VipCard vipCard=user.getVipCard();
        if (isFreeze){
            vipCardDao.freeze(vipCard);
        }else {
            vipCardDao.activate(vipCard);
        }
    }
}
