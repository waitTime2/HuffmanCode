package com.wait.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wait
 */
public class CharProcess {
    /**
     * 字符填充
     * @param source : 被填充字符串
     * @param index  : 填充起始位置
     * @param len : 填充后字符串长度
     * @param ch : 被填充字符
     * @return string
     */
    public static String fill(String source,int index,int len, char ch){
        if(index < 0 || index > source.length() || len < source.length()){
            String msg = String.format("len = %d; source = %s",len,source);
            throw new ArrayIndexOutOfBoundsException(msg);
        }
        StringBuilder buffer = new StringBuilder(source);
        for(int i = 0; i<len-source.length();i++){
            buffer.insert(index,ch);
        }
        return buffer.toString();
    }

    /**
     * 统计文本中字符
     * @param text 统计文本数据源
     * @return Map
     */
    public static Map<Character,Integer> charStat(String text){
        char[] chars = text.toCharArray();
        Map<Character,Integer> map = new HashMap<Character, Integer>(26);
        for(Character ch : chars){
            if(map.containsKey(ch)){
                map.replace(ch, map.get(ch) + 1);
            }else {
                map.put(ch, 1);
            }
        }
        return map;
    }

    public static Map<Character,Integer> charStat(File file)  {
        String text = null;
        try {
            text = FileRead.readText(file);
            return charStat(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二进制字符串转为字节组
     * 前提：字符串长度必须是 8 的倍数
     * @param varchar 二进制字符串
     * @return Object
     */
    public static String str2Bytes(String varchar) {
        int bitLen = 8;
        if(varchar.length() % bitLen !=0){
            throw new ArrayIndexOutOfBoundsException();
        }
        int count = varchar.length() / bitLen;
        byte[] bytes = new byte[count];
        for (int i = 0; i < count; i++) {
            int start = i * bitLen;
            byte aByte = (byte) Integer.parseInt(varchar.substring(start, start + bitLen), 2);
            bytes[i] = aByte;
        }
        String code = new String(bytes);
        return code;
    }

    /**
     * 保证每一字节都对应8为二进制字符串
     * @param bytes
     * @return
     */
    public static String bytes2str(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for(byte b : bytes){
            if(b<0){
                builder.append(Integer.toBinaryString(b).substring(24));
            }else {
                builder.append(CharProcess.fill(Integer.toBinaryString(b),0,8,'0'));
            }

        }
        return builder.toString();
    }
}