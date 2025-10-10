package lnu.hm222yj;

import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.Deque;

/*
An alternate traversal strategy is to recursively print out the left subtree, the right subtree,
and then the operator. PAge 109
 */

public class PostOrderIterator implements Iterator<Integer> {
    private Deque<MyNode> stack = new ArrayDeque<>();
    private MyNode traverseNode;
    private MyNode lastVisited;

    public PostOrderIterator(MyNode root) {
        this.traverseNode = root;
        this.lastVisited = null;
    }

    @Override
    public boolean hasNext() {
        return traverseNode != null || !stack.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            System.out.println("No next element, done!");
        }
        while (true) {
            while (traverseNode != null) {
                stack.push(traverseNode);
                traverseNode = traverseNode.leftNode;
            }
            MyNode topOfStack = stack.peek();
            if (topOfStack.rightNode != null && topOfStack.rightNode != lastVisited) {
                traverseNode = topOfStack.rightNode;
            } else {
                stack.pop();
                lastVisited = topOfStack;
                return topOfStack.nodeValue;
            }
        }
    }
}
