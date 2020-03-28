package com.wait.factory;

import com.exception.IgnoreRuleException;
import com.wait.bean.BinaryTree;
import com.wait.bean.Node;
import com.wait.bean.PwdTable;
import com.wait.util.CharProcess;

import java.awt.event.KeyEvent;
import java.util.*;

/**
 * 生产和保存密码表
 * @author Wait
 */
public class PwdTableFactory {
    private static List<PwdTable> pwdTables = new ArrayList<>(2);



    public static PwdTable getPwdTable(String tableName){
        //考虑到密码表一般数目比较少, 采用ArrayList查找速度不会有太大影响
        for(PwdTable table : pwdTables){
            if (table.getName().equals(tableName)){
                return table;
            }
        }
        throw new NullPointerException();
    }

    /**
     * 编码思路: 基于二叉树中序遍历(非递归法)实现对节点编码
     * 前提: 将每一个节点视为 "根节点", 左路径编为"0", 右路径编为"1"
     * 步骤:
     * 1. 连续向左孩子节点遍历,直至叶子节点
     * 2. 将叶子节点编码
     * 3. 跳转到右子树
     * 4. 重复1~3, 直至最右边的叶子节点退出遍历
     * 5. 最后将最右边的叶子节点编码
     * 堆栈:
     * 1. nodeStack : 保存中间节点
     * 2. codeStack:  保存中间节点的编码值, 与nodeStack保持一致(同进同出)
     * @param tree
     */
    public static void huffmanCode(BinaryTree tree){
        if(tree.getRootNode() == null){
            //根节点为空,异常
            throw new NullPointerException();
        }
        //创建密码映射表
        Map<Character,String> map = new HashMap<>(26);
        //创建一个代理节点
        Node proxyNode = tree.getRootNode();
        //记录中间节点
        Stack<Node> nodeStack = new Stack<>();
        //记录中间节点的编码
        Stack<String> codeStack = new Stack<>();
        //记录节点编码
        StringBuilder code = new StringBuilder();
        while(!nodeStack.empty() || proxyNode.getLeftChild() != null || proxyNode.getRightChild() != null){
            //遍历到左子树的的叶子节点
            while (proxyNode.getLeftChild() !=null){
                //保存中间节点及其编码
                nodeStack.push(proxyNode);
                codeStack.push(code.toString());
                //跳转到左孩子节点
                proxyNode = proxyNode.getLeftChild();
                //左路径编码 "0"
                code.append('0');
            }
            //保存叶子节点的code
            map.put(proxyNode.getNodeKey(), code.toString());
            NodeFactory.addNode(proxyNode);
            if(!nodeStack.empty()){
                //转到左子树
                proxyNode = nodeStack.pop();
                //恢复该节点的编码
                code.setLength(0);
                code.append(codeStack.pop());
                //转到右孩子节点
                proxyNode = proxyNode.getRightChild();
                //右路径编码 "1"
                code.append('1');
            }

        }
        //记录最右边的叶子节点的Code
        map.put(proxyNode.getNodeKey(), code.toString());
        NodeFactory.addNode(proxyNode);
        //保存已生成的密码表
        PwdTable<Character,String> huffman = new PwdTable<>();
        huffman.setName(tree.getName());
        huffman.setMap(map);
        pwdTables.add(huffman);
    }


}
