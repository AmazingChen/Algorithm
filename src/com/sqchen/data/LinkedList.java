package com.sqchen.data;

import com.sqchen.data.interfaces.Collection;

public class LinkedList<E> implements Collection<E> {

    /**
     * 头指针
     */
    private Node<E> head;

    /**
     * 尾指针
     */
    private Node<E> tail;

    private int size;

    public LinkedList() {
        //初始化时创建空的头指针和尾指针，并指向同一个节点，后续增加元素时，尾指针后移，但头指针一直不变
        head = new Node<E>();
        tail = head;
        size = 0;
    }

    @Override
    public void add(E e) {
        Node node = new Node<E>(e);
        //设置尾指针的下一个节点为node
        tail.next = node;
        //设置node为新的尾指针
        tail = node;
        //长度+1
        size++;
    }

    @Override
    public void insert(E e, int index) {
        verifyIndex(index, size);
        Node node = new Node<E>(e);
        //先遍历找到index-1结点，然后在index-1结点插入，复杂度O(n)
        int i = 0;
        //index - 1结点
        Node front = head;
        while (i < index) {
            front = front.next;
            i++;
        }
        node.next = front.next;
        front.next = node;
        size++;
        System.out.println(this.toString());
    }

    @Override
    public void delete(int index) {
        verifyIndex(index, size - 1);
        //找到index-1节点
        int i = 0;
        Node front = head;
        while (i < index) {
            front = front.next;
            i++;
        }
        Node target = front.next;
        front.next = target.next;
        target = null;
        size--;
        System.out.println(this.toString());
    }

    @Override
    public E get(int index) {
        verifyIndex(index, size - 1);
        Node node = head;
        int i = 0;
        while (i <= index) {
            node = node.next;
            i++;
        }
        return (E) node.element;
    }

    @Override
    public void modify(E e, int index) {
        verifyIndex(index, size - 1);
        Node node = head;
        int i = 0;
        while (i <= index) {
            node = node.next;
            i++;
        }
        node.element = e;
        System.out.println(this.toString());
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 判断操作的索引是否合法，
     * @param index
     * @param end   右边界，插入时允许在末尾插入，即end = size
     * @return
     */
    private void verifyIndex(int index, int end) {
        if (index < 0 || index > end) {
            throw new IndexOutOfBoundsException("invalid index for LinkedList：" + this.toString());
        }
    }

    @Override
    public String toString() {
        Node node = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (node.next != null) {
            node = node.next;
            stringBuilder.append(node.element + " ");
        }
        return stringBuilder.toString();
    }

    /**
     * 私有内部类，不允许外部访问
     * @param <E>
     */
    private static class Node<E> {
        E element;
        Node next;

        Node() {
        }

        Node(E e) {
            this.element = e;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        //增加
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        //插入
        linkedList.insert(12, 0);
        linkedList.insert(13, 0);
        //删除
        linkedList.delete(5);
        //查询
        System.out.println(linkedList.get(4));
        //修改
        linkedList.modify(100, 8);
    }

}
