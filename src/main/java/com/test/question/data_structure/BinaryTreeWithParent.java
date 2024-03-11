package com.test.question.data_structure;

public class BinaryTreeWithParent<T extends Comparable<T>> {
    private static class TreeNode<T extends Comparable<T>> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;
        TreeNode<T> parent;
        public TreeNode(T data, TreeNode<T> parent) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = parent;
        }
    }
    private TreeNode<T> root;
    public BinaryTreeWithParent() {
        this.root = null;
    }
    public void insert(T data) {
        root = insertRec(root, data, null);
    }
    private TreeNode<T> insertRec(TreeNode<T> current,
                                  T data, TreeNode<T> parent) {
        if (current == null) {
            return new TreeNode<>(data, parent);
        }
        if (data.compareTo(current.data) < 0) {
            current.left = insertRec(current.left, data, current);
        } else if (data.compareTo(current.data) > 0) {
            current.right = insertRec(current.right, data, current);
        } else {
            // value already exists
            return current;
        }
        return current;
    }
    public void delete(T data) {
        root = deleteRec(root, data);
    }
    private TreeNode<T> deleteRec(TreeNode<T> current, T data) {
        if (current == null) {
            return null;
        }
        if (data.equals(current.data)) {
            // Node to delete found
            if (current.left == null && current.right == null) {
                return null;
            } else if (current.right == null) {
                current.left.parent = current.parent;
                return current.left;
            } else if (current.left == null) {
                current.right.parent = current.parent;
                return current.right;
            } else {
                T smallestValue = findSmallestValue(current.right);
                current.data = smallestValue;
                current.right = deleteRec(current.right, smallestValue);
                return current;
            }
        }
        if (data.compareTo(current.data) < 0) {
            current.left = deleteRec(current.left, data);
        } else {
            current.right = deleteRec(current.right, data);
        }
        return current;
    }
    private T findSmallestValue(TreeNode<T> root) {
        return root.left == null ? root.data : findSmallestValue(root.left);
    }
    public TreeNode<T> search(T data) {
        return searchRec(root, data);
    }
    private TreeNode<T> searchRec(TreeNode<T> current, T data) {
        if (current == null || data.equals(current.data)) {
            return current;
        }

        if (data.compareTo(current.data) < 0) {
            return searchRec(current.left, data);
        } else {
            return searchRec(current.right, data);
        }
    }
    public void traversePreOrder(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    public void traverseInOrder(TreeNode<T> node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(node.data + " ");
            traverseInOrder(node.right);
        }
    }
    public void traversePostOrder(TreeNode<T> node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
    // Accessor for the root, to use for traversal
    public TreeNode<T> getRoot() {
        return root;
    }
    public static void main(String[] args) {
        BinaryTreeWithParent<Integer> bt =
                new BinaryTreeWithParent<>();
        // Insert elements
        bt.insert(5);
        bt.insert(3);
        bt.insert(7);
        bt.insert(2);
        bt.insert(4);
        bt.insert(6);
        bt.insert(1);
        // Search for element
        TreeNode<Integer> node = bt.search(4);
        System.out.println(node != null ? "找到节点: "
                + node.data : "节点没找到");
        // Traverse
        System.out.println("前序遍历:");
        bt.traversePreOrder(bt.getRoot());
        System.out.println("\n中序遍历:");
        bt.traverseInOrder(bt.getRoot());
        System.out.println("\n后序遍历:");
        bt.traversePostOrder(bt.getRoot());
        // Delete element
        bt.delete(3);
        // Traverse again to see the change
        System.out.println("\n删除元素后中序遍历:");
        bt.traverseInOrder(bt.getRoot());
    }
}

