package com.yaohua.designpattern.装饰器模式.案例一;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 文件名：HistorySet
 * 作者：huahua
 * 时间：2025/10/31 20:41
 * 描述  实现Set的功能， 并且额外能够将删除的元素记录下来；
 */
public class HistorySet<E> implements Set<E> {



    //通过构造函数 拿到Set的实现，由它来实现Set原有的功能；
    public HistorySet(Set<E> set) {
        this.delegate = set;
    }

    private final Set<E> delegate  ;


    //用来保存删除的元素
    private List<E> history = new ArrayList<>();


    @Override
    public boolean remove(Object o) {
        if (delegate.remove(o)){
            history.add((E) o);
            return true;
        }
        return false;
    }


    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegate.contains((E)o);
    }
    @Override
    public boolean add(E e) {
        return delegate.add(e);
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }





    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }


    @Override
    public String toString() {
        return delegate.toString()+ "remove list:" + history;
    }
}
