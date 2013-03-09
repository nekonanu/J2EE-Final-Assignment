package cn.edu.nju.dao.impl;

import cn.edu.nju.bean.Sale;
import cn.edu.nju.dao.ISaleDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-9
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SaleDaoImpl extends BaseDaoSupport<Sale> implements ISaleDao{
    @Override
    public boolean has(int id) {
        Query query=getSession().createQuery("select sa.id from Sale sa where sa.id=id");
        List<Integer> ids=query.list();
        if (ids.size()==0){
            return false;
        }else {
            return true;
        }
    }
}
