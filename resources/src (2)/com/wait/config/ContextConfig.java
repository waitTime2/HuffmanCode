package com.wait.config;

import com.wait.bean.BinaryTree;
import com.wait.factory.NodeFactory;
import com.wait.factory.PwdTableFactory;
import com.wait.factory.TreeFactory;
import com.wait.util.CharProcess;

import java.io.File;
import java.util.Map;

/**
 * @author Wait
 */
public class ContextConfig {
    /** 基本配置*/
    private String fileName;


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * 加载文件
     * @return File
     */
    public File loadFile()  {
        File file = new File(fileName);
        if(! file.exists()){
            //加载文件失败处理
        }
        return file;
    }

    /**
     * 获取 字符统计后的映射集
     * @return Map
     */
    public Map<Character, Integer> getMap(){
        Map<Character, Integer> map = CharProcess.charStat(loadFile());
        return map;
    }

    /**
     * 程序初始化入口
     */
    public void init(){
        //初始化Node容器
        NodeFactory.initNodeVector(getMap());
        //生成二叉树
        BinaryTree binaryTree = TreeFactory.createBinaryTree(fileName);
        //生产霍夫曼编码
        PwdTableFactory.huffmanCode(binaryTree);
    }


}
