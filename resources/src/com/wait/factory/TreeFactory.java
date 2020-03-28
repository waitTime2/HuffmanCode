package com.wait.factory;

import com.wait.bean.BinaryTree;
import com.wait.bean.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wait
 */
public class TreeFactory {
    private static Map<String, BinaryTree> treeMap = new HashMap<>(1);

    /**
     * 构建思路:
     * 1. 基于Node列表容器, 首先将其排序, 其中存储顺序是按照 Node 的 Value 从小到大排序的
     * 2. 取出index = 0 和 index = 1 ,即为容器中 value 最小的两个节点
     * 3. 创造出他们的父亲节点, 其中"0"节点为左孩子节点, "1"节点为左孩子节点
     * 4. 将父亲节点加入容器, 同时删除孩子节点
     * 5. 重复2~4步, 直至容器中只剩最后一个节点
     * 6. 创建二叉树对象, 并且将 最后节点设置为根节点
     * 7. 清空节点容器, 释放内存
     * @return BinaryTree
     */

    public static BinaryTree createBinaryTree(String treeName) {
        while (NodeFactory.getSize() > 1){
            //排序
            NodeFactory.sort();
            // 取出 "值最小" 的两个节点
            Node leftChild = NodeFactory.getNode(0);
            Node rightChild = NodeFactory.getNode(1);
            // 构造父节点
            int value = leftChild.getNodeValue() + rightChild.getNodeValue();
            Node parentNode = new Node('*', value);
            parentNode.setLeftChild(leftChild);
            parentNode.setRightChild(rightChild);
            //更新容器
            NodeFactory.addNode(parentNode);
            NodeFactory.deleteNode(leftChild);
            NodeFactory.deleteNode(rightChild);
        }
        //生成二叉树
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRootNode(NodeFactory.getNode(0));
        binaryTree.setName(treeName);
        //清空节点容器
        NodeFactory.destroyVector();
        //加入Tree容器中
        treeMap.put(treeName, binaryTree);
        return binaryTree;
    }

    public void DestroyTree(String treeName) {
        treeMap.remove(treeName);
    }

    public static BinaryTree getBinaryTree(String treeName){
        return treeMap.get(treeName);
    }
}

