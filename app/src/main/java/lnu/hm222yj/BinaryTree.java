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
            return;
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
        } else { // traverseNode.nodeValue <= newNode.nodeValue
            parentNode.rightNode = new MyNode(nodeValue);
        }
    }

    public boolean contains(int nodeValueToFind) {
        if (root == null) {
            return false;
        }
        MyNode traversalNode = root;
        while (traversalNode != null) {
            int currentNodeValue = traversalNode.nodeValue;
            if (nodeValueToFind < currentNodeValue) {
                traversalNode = traversalNode.leftNode;
            } else if (nodeValueToFind > currentNodeValue) {
                traversalNode = traversalNode.rightNode;
            } else {
                return true;
            }
        }
        return false;
    }

    public int height() {
        MyNode traverseNode = root;
        int height = -1;
        int currentHeight = 0;
        Deque<MyNode> traversalStack = new ArrayDeque<>();
        Deque<Integer> heightStack = new ArrayDeque<>();

        if (traverseNode == null) {
            return 0;
        }

        while (traverseNode != null || !traversalStack.isEmpty()) {
            while (traverseNode != null) {
                traversalStack.push(traverseNode);
                traverseNode = traverseNode.leftNode;
                heightStack.push(currentHeight);
                currentHeight++;
            }
            traverseNode = traversalStack.pop();
            traverseNode = traverseNode.rightNode;
            int receivedHeight = heightStack.pop();
            if (receivedHeight > height) {
                height = receivedHeight;
            }
            currentHeight = receivedHeight + 1;
        }
        return height + 1;
    }

    public int size() {
        MyNode traverseNode = root;
        int size = 0;
        Deque<MyNode> traversalStack = new ArrayDeque<>();
        if (traverseNode == null) {
            return 0;
        }
        while (traverseNode != null || !traversalStack.isEmpty()) {
            while (traverseNode != null) {
                traversalStack.push(traverseNode);
                traverseNode = traverseNode.leftNode;
            }
            traverseNode = traversalStack.pop();
            traverseNode = traverseNode.rightNode;
            size++;
        }
        return size;
    }

    public String remove(int numberToDelete) {
        MyNode parentNode = null;
        MyNode traverseNode = root;
        while (traverseNode != null) {
            if (numberToDelete < traverseNode.nodeValue) {
                parentNode = traverseNode;
                traverseNode = traverseNode.leftNode;
            } else if (numberToDelete > traverseNode.nodeValue) {
                parentNode = traverseNode;
                traverseNode = traverseNode.rightNode;
            } else {
                break;
            }
        }
        if (traverseNode == null) {
            return "Cannot delete " + numberToDelete + " does not exist!";
        }

        // 2 children
        if (traverseNode.leftNode != null && traverseNode.rightNode != null) {
            MyNode smallestNodeToSwapParent = traverseNode;
            MyNode smallestNodeToSwap = traverseNode.rightNode; // Always take the right subtree, page 191.
            while (smallestNodeToSwap.leftNode != null) {
                smallestNodeToSwapParent = smallestNodeToSwap;
                smallestNodeToSwap = smallestNodeToSwap.leftNode;
            }
            traverseNode.nodeValue = smallestNodeToSwap.nodeValue; // And then we just switch the values of the node
                                                                   // instead of the actual node object.

            MyNode smallestNodeToSwapChild = smallestNodeToSwap.rightNode;
            if (smallestNodeToSwapParent.leftNode == smallestNodeToSwap) {
                smallestNodeToSwapParent.leftNode = smallestNodeToSwapChild;
            } else {
                smallestNodeToSwapParent.rightNode = smallestNodeToSwapChild;
            }
            return "Deleted the number:  " + numberToDelete + "!";
        }

        // 1 or 0 children.
        if (traverseNode.leftNode != null) {
            MyNode childNode = traverseNode.leftNode;
            // When the deleting number is acutally the root we want to delete.
            if (parentNode == null) {
                root = childNode;
            } else if (parentNode.leftNode == traverseNode) {
                parentNode.leftNode = childNode;
            } else {
                parentNode.rightNode = childNode;
            }
            traverseNode.leftNode = null;
            traverseNode.rightNode = null;
        } else {
            MyNode childNode = traverseNode.rightNode;
            if (parentNode == null) {
                root = childNode;
            } else if (parentNode.leftNode == traverseNode) {
                parentNode.leftNode = childNode;
            } else {
                parentNode.rightNode = childNode;
            }
            traverseNode.leftNode = null;
            traverseNode.rightNode = null;
        }
        return "Deleted the number:  " + numberToDelete + "!";
    }

    public InOrderIterator inOrderIterator() {
        return new InOrderIterator(root);
    }

    public PreOrderIterator preOrderIterator() {
        return new PreOrderIterator(root);
    }

    // public Iterator<Integer> postOrderIterator() {
    // return new InOrderIterator(root);
    // }

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
        System.out.println("The size is: " + btree.size());
        System.out.println("And the height is: " + btree.height());
        System.out.println(btree.isEmpty());
        btree.add(30); // 1
        btree.add(3); // 2
        btree.add(8); // 3
        btree.add(14); // 4
        btree.add(15); // 5
        btree.add(2); // 6
        btree.add(12); // 7
        btree.add(19); // 8
        btree.add(9); // 9
        btree.add(89); // 10
        btree.add(24); // 11
        btree.add(49); // 12
        btree.add(29); // 13
        btree.add(18); // 14
        btree.add(13); // 15
        btree.add(7); // 16

        System.out.println(btree);
        System.out.println(btree.contains(3) + " this should be true");
        System.out.println(btree.contains(1113) + " this should be false");
        System.out.println(btree.contains(1) + " this should be false");
        System.out.println(btree.contains(89) + " this should be true");
        System.out.println("The size is: " + btree.size());
        System.out.println("And the height is: " + btree.height());
        System.out.println(btree.remove(3));
        System.out.println(btree.remove(8));
        System.out.println(btree.remove(14));
        System.out.println("The size is: " + btree.size());
        System.out.println("And the height is: " + btree.height());
        System.out.println(btree);

        System.out.println("INORDER: ");
        InOrderIterator inOrderIterator = btree.inOrderIterator();
        System.out.print("In order binary tree values: ");
        while (inOrderIterator.hasNext()) {
            int nodeValue = inOrderIterator.next();
            System.out.print(nodeValue + ", ");
        }
        System.out.println("");
        System.out.println("PREORDER: ");

        PreOrderIterator preOrderIterator = btree.preOrderIterator();
        System.out.print("Pre order binary tree values: ");
        while (preOrderIterator.hasNext()) {
            int nodeValue = preOrderIterator.next();
            System.out.print(nodeValue + ", ");
        }

        System.out.println("");
        System.out.println("POSTORDER: ");
    }
}
