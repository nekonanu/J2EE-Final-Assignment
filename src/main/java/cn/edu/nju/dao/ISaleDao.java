package cn.edu.nju.dao;

import cn.edu.nju.bean.Sale;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public interface ISaleDao extends IBaseDao<Sale> {
    public boolean has(int id);
}
