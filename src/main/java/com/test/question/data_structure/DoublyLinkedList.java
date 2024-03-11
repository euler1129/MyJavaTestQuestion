package com.test.question.data_structure;

public class DoublyLinkedList<T extends Comparable<T>> {
    private class DLLNode {
        T data;
        DLLNode prev;
        DLLNode next;
        public DLLNode(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
    DLLNode head;
    DLLNode tail;
    public DoublyLinkedList() {
        head = null;
        tail = null;
    }
    // 在链表尾部添加新节点
    public void append(T data) {
        DLLNode newNode = new DLLNode(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    // 在链表头部添加新节点
    public void prepend(T data) {
        DLLNode newNode = new DLLNode(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
    }
    // 删除节点
    public void delete(T data) {
        DLLNode current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }
    // 查找
    public T search(T data) {
        DLLNode temp = head;
        while (temp != null) {
            if(temp.data.equals(data)){
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }
    // 排序
    public void sort() {
        if (head == null || head.next == null) {
            return;
        }
        DLLNode current = head;
        while (current.next != null) {
            if (current.data.compareTo(current.next.data) > 0) {
                T temp = current.data;
                current.data = current.next.data;
                current.next.data = temp;
            }
            current = current.next;
        }
    }
    // 遍历 - 打印链表
    public void printList() {
        DLLNode temp = head;
        while (temp != null) {
            System.out.print(temp.data.toString() + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public static void main(String[] args){
        DoublyLinkedList<Integer> doublyLinkedList =
                new DoublyLinkedList<>();
        doublyLinkedList.append(2);
        doublyLinkedList.append(1);
        doublyLinkedList.append(5);
        doublyLinkedList.append(3);
        doublyLinkedList.append(4);
        System.out.println("遍历泛型双向链表：" );
        doublyLinkedList.printList();
        System.out.println("5被找到：" +
                doublyLinkedList.search(5));
        System.out.println("10没有被找到：" +
                doublyLinkedList.search(10));
        doublyLinkedList.sort();
        System.out.println("排序后的泛型双向链表：" );
        doublyLinkedList.printList();
    }
}
