package it.test.simple.shu;

import java.util.*;

public class Solution {


    //中序遍历

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        middleSearch(ans, root);
        return ans;
    }

    private void middleSearch(List<Integer> ans, TreeNode root) {
        if (root == null) {
            return;
        }
        middleSearch(ans, root.left);
        ans.add(root.val);
        middleSearch(ans, root.right);
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

    //2叉树前序迭代
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> ans=new ArrayList<>();//结果集
        Stack<TreeNode> stack=new Stack<>();
        if(root==null){
            return ans;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur=stack.pop();
            ans.add(cur.val);//加入当前元素
            if(cur.right!=null){//将右子节点加入栈中(栈先进后出，先入右再入左,则先取左再取右)
                stack.push(cur.right);
            }
            if(cur.left!=null){//将左子结点加入栈中
                stack.push(cur.left);
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
