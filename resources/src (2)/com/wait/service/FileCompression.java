
package com.wait.service;

import java.io.File;
import java.io.IOException;

/**
 * 文件压缩
 * @Author Wait
 */
public interface FileCompression {
    /**
     * 编码
     * @param fileName ：源文件名（文本，图片，视频等）
     * @param dictFile ： 被压缩文件
     */
    public void encode(String fileName, File dictFile) throws IOException;

    /**
     * 解码（恢复）
     * @param fileName ： 压缩文件
     * @param dictFile ： 解压文件（文本，图片，视频等）
     */
    public void decode(String  fileName, File dictFile);
}

