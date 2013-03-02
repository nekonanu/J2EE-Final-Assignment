package cn.edu.nju.util;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-2
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class LinkedItem<T> {

    private LinkedItem linkedItem;
    private T content;

    public LinkedItem(T content){
        this.content=content;
    }

    public void setLinkedItem(LinkedItem linkedItem) {
        this.linkedItem = linkedItem;
    }

    public LinkedItem getLinkedItem() {
        return linkedItem;
    }

    public T getContent() {
        return content;
    }
}
