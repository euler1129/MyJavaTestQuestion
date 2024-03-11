package com.test.question.data_structure;

public class QuadrupleLinkedList<T> {
    private class QLLNode<T> {
        T data;
        QLLNode<T> up;
        QLLNode<T> down;
        QLLNode<T> left;
        QLLNode<T> right;
        public QLLNode(T data) {
            this.data = data;
            this.up = null;
            this.down = null;
            this.left = null;
            this.right = null;
        }
    }
    QLLNode<T> head;
    // 初始化一个空的四向链表
    public QuadrupleLinkedList() {
        head = null;
    }
    // 插入节点到指定位置
    public void insert(int x, int y, T data) {
        if (head == null) {
            if (x == 0 && y == 0) {
                head = new QLLNode(data);
            } else {
                throw new IllegalArgumentException(
                        "Invalid coordinates for insertion");
            }
        } else {
            QLLNode<T> current = head;
            // 移动到正确的行
            for (int i = 0; i < x; i++) {
                if (current.down == null) {
                    // 假设默认值为0
                    current.down = new QLLNode(0);
                    current.down.up = current;
                }
                current = current.down;
            }
            // 移动到正确的列
            for (int j = 0; j < y; j++) {
                if (current.right == null) {
                    // 假设默认值为0
                    current.right = new QLLNode(0);
                    current.right.left = current;
                }
                current = current.right;
            }
            // 插入新节点
            current.data = data;
        }
    }
    // 删除指定位置的节点
    public void delete(int x, int y) {
        if (head == null) {
            throw new IllegalStateException(
                    "The grid is empty");
        }
        // 寻找目标节点
        QLLNode<T> current = head;
        for (int i = 0; i < x; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException(
                        "Y coordinate out of bounds");
            }
            current = current.down;
        }
        for (int j = 0; j < y; j++) {
            if (current == null) {
                throw new IndexOutOfBoundsException(
                        "X coordinate out of bounds");
            }
            current = current.right;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException(
                    "No node found at the given coordinates");
        }
        // 重新连接目标节点的邻居
        if (current.up != null) {
            current.up.down = current.down;
        }
        if (current.down != null) {
            current.down.up = current.up;
        }
        if (current.left != null) {
            current.left.right = current.right;
        }
        if (current.right != null) {
            current.right.left = current.left;
        }
        // 如果删除的是头节点，需要更新头节点
        if (x == 0 && y == 0) {
            if (current.right != null) {
                current.right.left = null;
                head = current.right;
            } else if (current.down != null) {
                current.down.up = null;
                head = current.down;
            } else {
                head = null;
            }
        }
    }
    // 搜索指定值的节点
    public QLLNode search(T data) {
        QLLNode<T> row = head;
        while (row != null) {
            QLLNode<T> col = row;
            while (col != null) {
                if (col.data.equals(data)) {
                    return col;
                }
                col = col.right;
            }
            row = row.down;
        }
        return null;
    }
    // 遍历四向链表
    public void traverse() {
        QLLNode<T> row = head;
        while (row != null) {
            QLLNode<T> col = row;
            while (col != null) {
                System.out.print(col.data + " ");
                col = col.right;
            }
            System.out.println();
            row = row.down;
        }
    }
    // 打印网格
    public void printGrid() {
        traverse();
    }
    public static void main(String[] args){
        QuadrupleLinkedList<Integer> quadrupleLinkedList =
                new QuadrupleLinkedList<>();
        quadrupleLinkedList.insert(0, 0, 1);
        quadrupleLinkedList.insert(0, 1, 2);
        quadrupleLinkedList.insert(0, 2, 3);
        quadrupleLinkedList.insert(1, 0, 4);
        quadrupleLinkedList.insert(1, 1, 5);
        quadrupleLinkedList.insert(1, 2, 6);
        quadrupleLinkedList.insert(2, 0, 7);
        quadrupleLinkedList.insert(2, 1, 8);
        quadrupleLinkedList.insert(2, 2, 9);
        System.out.println("遍历四向链表：" );
        quadrupleLinkedList.printGrid();
        System.out.println("5被找到：" +
                quadrupleLinkedList.search(5).data);
        System.out.println("10没有被找到：" +
                quadrupleLinkedList.search(10));
        quadrupleLinkedList.delete(1, 1);
        System.out.println("删除第二行第二列的元素5后，再遍历四向链表：" );
        quadrupleLinkedList.printGrid();
    }
}
