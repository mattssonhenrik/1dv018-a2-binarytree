package lnu.hm222yj;

import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.Deque;

public class PreOrderIterator implements Iterator<Integer> {
    private Deque<MyNode> stack = new ArrayDeque<>();
    private MyNode traverseNode;

    public PreOrderIterator(MyNode root) {
        this.traverseNode = root;
    }

    @Override
    public boolean hasNext() {
        if (traverseNode != null) {
            return true;
        }
        for (MyNode parentsNode : stack) {
            if (parentsNode.rightNode != null)
                return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            System.out.println("No next element, done!");
        }
        while (true) {
            while (traverseNode != null) {
                int result = traverseNode.nodeValue;
                stack.push(traverseNode);
                traverseNode = traverseNode.leftNode;
                return result;

            }
            traverseNode = stack.pop();
            traverseNode = traverseNode.rightNode;
        }
    }
}
