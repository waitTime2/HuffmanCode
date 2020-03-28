package huffman;

import java.io.File;

/**
 * @author Wait
 */
public class PerformanceTest {

    public static void CompressionRatio(File in, File out){
        long inLen = in.length();
        long outLen = out.length();
        System.out.println("Original size : " + inLen);
        System.out.println("Compressed size : " + outLen);
        System.out.println("Compressed ratio : " + ((float)outLen /inLen)*100 + " %");
    }
}
