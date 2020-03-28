package com.wait.factory;

import com.wait.bean.Node;

import java.util.*;

/**
 * @author Wait
 */
public class NodeFactory {
    private static List<Node> nodeList;

    /**
     * 工厂初始化
     * @param map 字符统计的映射集. 用于生成 Node
     */
    public static void initNodeVector(Map<Character,Integer> map){
        nodeList = new ArrayList<Node>(30);
        //生成节点,放入列表容器
        Set<Character> chars = map.keySet();
        for(char ch : chars){
            nodeList.add(new Node(ch,map.get(ch)));
        }

    }

    /**
     * 获取容器大小
     * @return
     */
    public static int getSize(){
        return nodeList.size();
    }

    /**
     * 清空容器
     */
    public static void destroyVector(){
        nodeList.clear();
    }

    /**
     * 向容器中增加新的节点
     * @param newNode 新节点
     */
    public static void addNode(Node newNode){
        if(newNode == null){
            throw new NullPointerException();
        }
        nodeList.add(newNode);
    }

    /**
     * 删除节点
     * @param oldNode 被删节点
     */
    public static void deleteNode(Node oldNode){
        if(oldNode == null){
            throw new NullPointerException();
        }
        nodeList.remove(oldNode);
    }

    /**
     * 获取节点
     * @return
     */
    public static Node getNode(int index){
        return nodeList.get(index);
    }


    /**
     * 获取节点列表
     * @return List
     */
    public static List<Node> getNodeList() {
        return nodeList;
    }

    /**
     * 获取顺序节点列表
     * @return List
     */
    public static List<Node> getOrderNodeList(){
        sort();
        return nodeList;
    }

    /**
     * 排序(从小到大)
     * @return
     */
    public static void sort() {
        nodeList.sort(new Comparator<Node>() {
            @Override
            public int compare(Node arg0, Node arg1) {

                return arg0.getNodeValue().compareTo(arg1.getNodeValue());
            }
        });
    }


}
