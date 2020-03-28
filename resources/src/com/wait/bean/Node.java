package com.wait.bean;

import java.io.Serializable;

/**
 * @author Wait
 */
public class Node implements Serializable {
    private Character nodeKey;
    private Integer nodeValue;
    private Node leftChild;
    private Node rightChild;

    public Node(Character nodeKey, Integer nodeValue) {
        this.nodeKey = nodeKey;
        this.nodeValue = nodeValue;
    }

    public Character getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(Character nodeKey) {
        this.nodeKey = nodeKey;
    }

    public Integer getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(Integer nodeValue) {
        this.nodeValue = nodeValue;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}

