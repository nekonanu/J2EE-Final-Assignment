package cn.edu.nju.dao;

import cn.edu.nju.bean.UserEntity;
import cn.edu.nju.util.LinkedItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午7:22
 * To change this template use File | Settings | File Templates.
 */
public interface IUserDao extends IBaseDao<UserEntity>{
    public LinkedItem getUserAndCard(int user_id);
    public List getAllUserAndCard();
}
