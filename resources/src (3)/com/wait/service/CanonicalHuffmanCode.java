package com.wait.service;

import java.io.File;
import java.io.IOException;

/**
 * 范式霍夫曼编码
 *
 * @author Wait
 */
public class CanonicalHuffmanCode implements FileCompression {
    /**
     * 编码
     *
     * @param fileName ：源文件名（文本，图片，视频等）
     * @param dictFile ： 被压缩文件
     */
    @Override
    public void encode(String fileName, File dictFile) throws IOException {

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
}
