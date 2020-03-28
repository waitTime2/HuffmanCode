
package com.wait.bean;

/**
 * @author Wait
 */
public class BinaryTree {
    private String name;
    private Node rootNode;
    private int depth;
    private Integer nodeNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node headNode) {
        this.rootNode = headNode;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }
}

