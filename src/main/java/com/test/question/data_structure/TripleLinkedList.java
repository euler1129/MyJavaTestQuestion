package com.test.question.data_structure;

public class TripleLinkedList<T> {
    private class TLLNode<T> {
        T data;
        TLLNode<T> prev;
        TLLNode<T> next;
        TLLNode<T> arbitrary; // 指向任意节点的指针
        public TLLNode(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
            this.arbitrary = null;
        }
    }
    TLLNode<T> head;
    // 插入新节点到链表头部
    public void insertAtHead(T data) {
        TLLNode<T> newNode = new TLLNode<>(data);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
    }
    // 删除节点
    public void deleteNode(TLLNode<T> nodeToDelete) {
        if (nodeToDelete == null) {
            return;
        }
        if (nodeToDelete.prev != null) {
            nodeToDelete.prev.next = nodeToDelete.next;
        } else {
            head = nodeToDelete.next;
        }
        if (nodeToDelete.next != null) {
            nodeToDelete.next.prev = nodeToDelete.prev;
        }
    }
    // 搜索节点
    public TLLNode<T> search(T data) {
        TLLNode<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    // 更新任意指针
    public void updateArbitrary(TLLNode<T> node,
                                TLLNode<T> arbitraryNode) {
        if (node != null) {
            node.arbitrary = arbitraryNode;
        }
    }
    // 打印链表
    public void printList() {
        TLLNode<T> temp = head;
        while (temp != null) {
            String dataStr = temp.data.toString();
            String arbDataStr = (temp.arbitrary != null) ?
                    temp.arbitrary.data.toString() : "null";
            System.out.println("Node Data: " + dataStr
                    + ", Arbitrary Node Data: " + arbDataStr);
            temp = temp.next;
        }
    }
    public static void main(String[] args){
        TripleLinkedList<Integer> tripleLinkedList =
                new TripleLinkedList<>();
        tripleLinkedList.insertAtHead(2);
        tripleLinkedList.insertAtHead(1);
        tripleLinkedList.insertAtHead(5);
        tripleLinkedList.insertAtHead(3);
        tripleLinkedList.insertAtHead(4);
        System.out.println("遍历三向链表：" );
        tripleLinkedList.printList();
        System.out.println("5被找到：" +
                tripleLinkedList.search(5).data);
        System.out.println("10没有被找到：" +
                tripleLinkedList.search(10));
    }
}
