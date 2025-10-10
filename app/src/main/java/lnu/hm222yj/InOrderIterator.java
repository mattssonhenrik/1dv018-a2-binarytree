package lnu.hm222yj;

import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.Deque;

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
            MyNode node = stack.pop();
            int result = node.nodeValue;
            traverseNode = node.rightNode;

            return result;
        }
    }
