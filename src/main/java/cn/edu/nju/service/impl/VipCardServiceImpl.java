package cn.edu.nju.service.impl;

import cn.edu.nju.bean.ChargeLog;
import cn.edu.nju.bean.User;
import cn.edu.nju.bean.VipCard;
import cn.edu.nju.dao.IChargeLogDao;
import cn.edu.nju.dao.IUserDao;
import cn.edu.nju.dao.IVipCardDao;
import cn.edu.nju.service.IVipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午6:53
 * To change this template use File | Settings | File Templates.
 */
@Service
public class VipCardServiceImpl implements IVipCardService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IVipCardDao vipCardDao;
    @Autowired
    private IChargeLogDao chargeLogDao;



    @Override
    public void addVipCard(VipCard vipCard) {
        vipCardDao.save(vipCard);
    }

    @Override
    public void cardCharge(User user, int amount) {
        VipCard vipCard=user.getVipCard();
        vipCardDao.charge(vipCard,amount);
        ChargeLog chargeLog=new ChargeLog();
        chargeLog.setUser(user);
        chargeLog.setChargeAmount(amount);
        chargeLog.setChargeDate(new Date());
        chargeLog.setVipCard(vipCard);
        chargeLogDao.save(chargeLog);
    }

    @Override
    public void buyByCard(User user, double amount) {
        VipCard card=user.getVipCard();
        card.setRemainAmount(card.getRemainAmount()-amount);
        vipCardDao.update(card);
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
