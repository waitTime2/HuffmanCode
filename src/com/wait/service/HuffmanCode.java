package com.wait.service;

import com.wait.bean.PwdTable;
import com.wait.config.ContextConfig;
import com.wait.factory.PwdTableFactory;
import com.wait.util.CharProcess;
import com.wait.util.FileRead;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Wait
 */
public class HuffmanCode implements FileCompression {


    /**
     * 编码
     *
     * @param fileName ：源文件名（文本，图片，视频等）
     * @param dictFile ： 被压缩文件
     */
    @Override
    public void encode(String fileName, File dictFile) throws IOException  {
        Map<Character,String> codeMap = PwdTableFactory.getPwdTable(fileName).getMap();
        int ch;
        StringBuilder builder = new StringBuilder();
        FileReader reader = new FileReader(fileName);
        FileOutputStream out = new FileOutputStream(dictFile);
        while (true){
            ch = reader.read();
            if (ch < 0){
                break;
            }
            builder.append(codeMap.get((char)ch));
        }
//        byte[] bytes = str2bytes(builder.toString());
        out.write(builder.toString().getBytes());
        //关闭数据流
        reader.close();
        out.close();
    }

    /**
     * 解码（恢复）
     *
     * @param fileName ： 压缩文件
     * @param dictFile ： 解压文件（文本，图片，视频等）
     */
    @Override
    public void decode(String fileName, File dictFile) {

    }



    public byte[] str2bytes(String varchar){
        int len = varchar.length();
        StringBuilder code = new StringBuilder();
        int start =  len - (len % 8);
        code.append(CharProcess.str2Bytes(varchar.substring(0,start)));
        //最后几个bit直接编码
        code.append(varchar.substring(start));
        byte[] bytes = new byte[0];
        try {
            bytes = code.toString().getBytes("iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
