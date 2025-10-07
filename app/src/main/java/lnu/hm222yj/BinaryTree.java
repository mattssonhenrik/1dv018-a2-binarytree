package lnu.hm222yj;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinaryTree {
    MyNode root = null;

    public boolean isEmpty() {
        return root == null;
    }

    public void add(int nodeValue) {
        if (root == null) {
            root = new MyNode(nodeValue);
        }

        MyNode traverseNode = root;
        MyNode parentNode = null;

        while (traverseNode != null) {
            parentNode = traverseNode;
            if (nodeValue < traverseNode.nodeValue) {
                traverseNode = traverseNode.leftNode;
            } else { // traverseNode.nodeValue < newNode.nodeValue
                traverseNode = traverseNode.rightNode;
            }
        }

        if (nodeValue < parentNode.nodeValue) {
            parentNode.leftNode = new MyNode(nodeValue);
            System.out.println("IF: added node with value " + nodeValue);
        } else { // traverseNode.nodeValue <= newNode.nodeValue
            parentNode.rightNode = new MyNode(nodeValue);
            System.out.println("ELSE: added node with value " + nodeValue);
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Deque<MyNode> stack = new ArrayDeque<>();
        MyNode traversalNode = root;
        while (traversalNode != null || !stack.isEmpty()) {
            while (traversalNode != null) {
                stack.push(traversalNode);
                traversalNode = traversalNode.leftNode;
            }
            MyNode node = stack.pop();
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(node.nodeValue);
            traversalNode = node.rightNode;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        BinaryTree btree = new BinaryTree();
        System.out.println(btree.isEmpty());
        btree.add(10);
        btree.add(3);
        btree.add(8);
        btree.add(14);
        btree.add(15);
        btree.add(2);
        btree.add(12);
        btree.add(19);
        System.out.println(btree);

    }
}
