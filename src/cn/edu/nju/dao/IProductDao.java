package cn.edu.nju.dao;

import cn.edu.nju.bean.ProductEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
public interface IProductDao extends IBaseDao<ProductEntity>{
    public ProductEntity findByName(String name);
    public List<ProductEntity> getAllAvailableProduct();
}
