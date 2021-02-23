package it.test.simple.shu;

import java.util.*;

public class Solution {


    //中序遍历

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    //前序遍历

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    //N叉树后续
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<Node> s = new Stack<Node>();
        if (root == null)
            return ans;
        s.push(root);

        while (!s.isEmpty()) {
            Node curr = s.pop();
            ans.addFirst(curr.val);
            for (Node child : curr.children) {
                if (child != null)
                    s.push(child);
            }
        }
        return ans;
    }

//    N 叉树前序 递归

    List<Integer> list = new ArrayList<Integer>();
    public List<Integer> preorder(Node root) {
        if (root == null)
            return list;
        list.add(root.val);
        for (Node node : root.children)
            preorder(node);
        return list;
    }

    public static void main(String[] args) {


    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
