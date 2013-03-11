package cn.edu.nju.dao;


import cn.edu.nju.bean.ProductOrder;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-2
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
public interface IOrderDao extends IBaseDao<ProductOrder>{
    public List<ProductOrder> findBetweenDate(Date begin,Date end,int store_id);
}
