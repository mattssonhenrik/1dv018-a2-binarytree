package lnu.hm222yj;

public class BinaryTree {
    MyNode root = null;

    public boolean contains() {
        return root != null;
    }

    public static void main(String[] args) {
        BinaryTree btree = new BinaryTree();
        System.out.println(btree.contains());
    }
}
