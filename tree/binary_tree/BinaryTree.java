package tree.binary_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class BinaryTree<T> {
    TreeNode<T> root;

    TreeNode<Integer> flattenTree(TreeNode<Integer> root) {
        TreeNode<Integer> current = root;
        while (current != null) {
            if (current.left != null) {
                TreeNode<Integer> rightMost = current.left;
                TreeNode<Integer> nextNode = current.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                rightMost.right = current.right;
                current.right = current.left;
                current.left = null;
                current = nextNode;
            } else {
                current = current.right;
            }
            
        }
        return root;
    }

    List<TreeNode<T>> flatten(TreeNode<T> root) {
        if (root == null) return new ArrayList<>(){};
        TreeNode<T> left = root.left;
        TreeNode<T> right = root.right;
        TreeNode<T> tail = root;
        if (left != null) {
            List<TreeNode<T>> leftFlattenned = flatten(root.left);
            root.left = null;
            root.right = leftFlattenned.get(0);
            tail = leftFlattenned.get(1);
        }
        if (right != null) {
            List<TreeNode<T>> rightFlattenned = flatten(right);
            tail.right = rightFlattenned.get(0);
            tail = rightFlattenned.get(1);
        }
        tail.right = null;
        return new ArrayList<>(Arrays.asList(root,  tail));
    }

    void printFlattened(TreeNode<T> root) {
        while (root != null) {
            System.out.print(root.data + "-> ");
            root = root.right;
        }
        System.out.print("NULL");
    }

    public void createBinaryTree(List<TreeNode<T>> nodeList) {
        if (nodeList == null) return;
        root = nodeList.get(0);
        Queue<TreeNode<T>> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < nodeList.size()) {
            TreeNode<T> curr = q.poll();
            if (curr == null) {
                continue;
            }
            curr.left = nodeList.get(i);
            q.offer(nodeList.get(i));
            i ++;
            if (nodeList.size() <= i) {
                break;
            }
            curr.right = nodeList.get(i);
            q.offer(nodeList.get(i));
            i ++;
        }
        System.out.println("Done");
    }
    // Function to print the binary tree
    public static void printTree(TreeNode root, String prefix, boolean isLeft) {
        if (root != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.data);
            printTree(root.left, prefix + (isLeft ? "│   " : "    "), true);
            printTree(root.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public static void main(String args[]) {
        List<List<TreeNode<Integer>>> listOfTrees = Arrays.asList( 
			Arrays.asList(new TreeNode<Integer>(3), new TreeNode<Integer>(2), new TreeNode<Integer>(17), new TreeNode<Integer>(1), new TreeNode<Integer>(4), new TreeNode<Integer>(19), new TreeNode<Integer>(5)),
			Arrays.asList(new TreeNode<Integer>(7), new TreeNode<Integer>(6), new TreeNode<Integer>(5), new TreeNode<Integer>(4), new TreeNode<Integer>(3), new TreeNode<Integer>(2), null, new TreeNode<Integer>(1)),
			Arrays.asList(new TreeNode<Integer>(5), new TreeNode<Integer>(4), new TreeNode<Integer>(6), new TreeNode<Integer>(3), new TreeNode<Integer>(2), new TreeNode<Integer>(7), new TreeNode<Integer>(8), new TreeNode<Integer>(1), new TreeNode<Integer>(9)),
			Arrays.asList(new TreeNode<Integer>(5), new TreeNode<Integer>(2), new TreeNode<Integer>(1), new TreeNode<Integer>(6), new TreeNode<Integer>(10), new TreeNode<Integer>(11), new TreeNode<Integer>(44)),
			Arrays.asList(new TreeNode<Integer>(1), new TreeNode<Integer>(2), new TreeNode<Integer>(5), new TreeNode<Integer>(3), new TreeNode<Integer>(4), new TreeNode<Integer>(6)),
			Arrays.asList(new TreeNode<Integer>(-1), new TreeNode<Integer>(-2), null, new TreeNode<Integer>(-5), new TreeNode<Integer>(1), new TreeNode<Integer>(2), null, new TreeNode<Integer>(-6))
		);
        
        // Create the binary trees using the BinaryTree class
        List<BinaryTree<Integer>> inputTrees = new ArrayList<BinaryTree<Integer>>();
        for (List<TreeNode<Integer>> ListOfNodes : listOfTrees) {
            BinaryTree<Integer> tree = new BinaryTree<Integer>();
            tree.createBinaryTree(ListOfNodes);
            printTree(tree.root, "", true);
            TreeNode<Integer> flattened = tree.flattenTree(tree.root);
            tree.printFlattened(flattened);
            inputTrees.add(tree);
        }

    }
}
