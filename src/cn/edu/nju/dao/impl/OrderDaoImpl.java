package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.ProductOrder;
import cn.edu.nju.dao.IOrderDao;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-2
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class OrderDaoImpl extends BaseDaoSupport<ProductOrder> implements IOrderDao {
}
