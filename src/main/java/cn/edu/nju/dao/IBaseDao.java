package cn.edu.nju.dao;

import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 上午11:28
 * To change this template use File | Settings | File Templates.
 */
public interface IBaseDao<T> {
    void save(T t);

    void update(T t);

    void deleteById(int i);

    void merge(T t);

    T findById(int i);

    List<T> findByCriteria(Criterion... criterions);
}
