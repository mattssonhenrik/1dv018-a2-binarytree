package lnu.hm222yj;

import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.Deque;

/* 
 We can produce an (overly parenthesized) infix expression by recursively producing a
parenthesized left expression, then printing out the operator at the root, and finally recursively
producing a parenthesized right expression. This general strategy (left, node, right)
is known as an inorder traversal. Page 109.
 */
public class InOrderIterator implements Iterator<Integer> {
    private Deque<MyNode> stack = new ArrayDeque<>();
    private MyNode traverseNode;

    public InOrderIterator(MyNode root) {
        this.traverseNode = root;
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
        while (traverseNode != null) {
            stack.push(traverseNode);
            traverseNode = traverseNode.leftNode;

        }
        traverseNode = stack.pop();
        int result = traverseNode.nodeValue;
        traverseNode = traverseNode.rightNode;

        return result;
    }
}
