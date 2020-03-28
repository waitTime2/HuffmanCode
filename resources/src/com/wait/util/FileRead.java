package com.wait.util;

import java.io.*;

/**
 * @author Wait
 */
public class FileRead {


    public static String readText(File file) throws IOException {
        FileReader reader = new FileReader(file);
        StringBuffer buffer = new StringBuffer();
        int ch ;
        while(true){
            ch = reader.read();
            if (ch == -1){
                break;
            }
            buffer.append((char)ch);
        }
        reader.close();
        return buffer.toString();
    }



}

