package com.sqchen.data.interfaces;

public interface Collection<E> {

    void add(E e);

    void insert( E e, int index);

    void delete(int index);

    E get(int index);

    void modify( E e,int index);

    boolean isEmpty();

    int size();

}
