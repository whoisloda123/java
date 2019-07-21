package com.liucan.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liucan
 * @version 19-7-20
 */
public class BinaryTreeUtil {

    @Data
    class BinTree<T> {
        BinTree left;
        BinTree right;
        T value;
    }

    public static void main(String[] args) {
        Integer[] arrays = {1, 2, 3, 4, 5};
        BinaryTreeUtil document = new BinaryTreeUtil();
        BinTree root = document.created(arrays);

        //document.PreOrder(root);
        document.bianliBinaryTree(root);
    }

    //先根遍历
    public void PreOrder(BinTree node) {
        if (node != null) {
            System.out.println(node.getValue()); //先访问根节点
            PreOrder(node.getLeft());  //先根遍历左子树
            PreOrder(node.getRight());  //先根遍历右子树
        }
    }

    public <T> BinTree created(T data[]) {//初始化二叉树,将传进来的值创建为二叉树
        List<BinTree> list = new ArrayList<>();//新建一个list集合，将数据变为各个节点
        for (T tempdata : data) {
            BinTree binTree = new BinTree();
            binTree.setValue(tempdata);
            list.add(binTree);
        }
        BinTree root = list.get(0);//将第一个元素设置为根节点
        /**
         * 利用构建完全二叉树的方式构建
         */
        for (int i = 0; i < list.size() / 2; i++) {
            if ((i * 2 + 1) < list.size()) {
                list.get(i).setLeft(list.get(i * 2 + 1));
            }
            if ((i * 2 + 2) < list.size()) {

                list.get(i).setRight(list.get(i * 2 + 2));
            }
        }
        return root;
    }

    //按照层次遍历二叉树
    public void bianliBinaryTree(BinTree root) {
        LinkedList<BinTree> binTrees = new LinkedList<>();
        binTrees.add(root);
        BinTree pop;
        do {
            pop = binTrees.pop();
            System.out.println(pop.getValue());
            if (pop.getLeft() != null) {
                binTrees.add(pop.getLeft());
            }
            if (pop.getRight() != null) {
                binTrees.add(pop.getRight());
            }
        } while (!binTrees.isEmpty());
    }
}
