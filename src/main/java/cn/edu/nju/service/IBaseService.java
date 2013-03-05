package cn.edu.nju.service;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-4
 * Time: 下午6:05
 * To change this template use File | Settings | File Templates.
 */
public interface IBaseService<T> {
    void save(T t);

    void update(T t);

    void deleteById(int i);

    T findById(int i);
}
