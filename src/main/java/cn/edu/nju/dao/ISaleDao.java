package cn.edu.nju.dao;

import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.bean.Sale;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public interface ISaleDao extends IBaseDao<Sale> {
    public boolean has(int id);
    public List<Sale> findBetweenDate(Date begin,Date end,int store_id);
}
