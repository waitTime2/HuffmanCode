package com.wait;

import com.wait.bean.BinaryTree;
import com.wait.bean.Node;
import com.wait.config.ContextConfig;
import com.wait.factory.PwdTableFactory;
import com.wait.factory.TreeFactory;
import com.wait.service.FileCompression;
import com.wait.service.HuffmanCode;

import java.io.*;
import java.util.Map;
import java.util.Stack;

/**
 * @author Wait
 */
public class MainActivity {
    public static void traverse(Node root){
        Node proxy = root;
        Stack<Node> nodeStack = new Stack<>();
        while (!nodeStack.empty() || proxy!=null){
            if(proxy!=null){
                System.out.print(proxy.getNodeKey());
                nodeStack.push(proxy);
                proxy = proxy.getLeftChild();
            }
            else{
                proxy = nodeStack.pop();
                proxy = proxy.getRightChild();
            }
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String in = "resources/text2";
        ContextConfig config = new ContextConfig();
        config.setFileName(in);
        config.init();
        BinaryTree binaryTree = TreeFactory.getBinaryTree(in);
        traverse(binaryTree.getRootNode());
        System.out.println(PwdTableFactory.getPwdTable(in).getMap());
        HuffmanCode huffmanCode = new HuffmanCode();
        huffmanCode.encode(in, new File("resources/out"));
    }
}
