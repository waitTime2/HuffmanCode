package huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wait
 */
public class Decoder {
    private ByteInputStream in;
    private BufferedOutputStream out;
    private HuffmanTree huffman;

    public static void main(String[] args) throws IOException {
        File in = new File("resources/mid.txt");
        File out = new File("resources/out");
        Decoder decoder = new Decoder(in, out);
        long start = System.currentTimeMillis();
        decoder.decode();
        long end = System.currentTimeMillis();
        System.out.println("Decompression time:  " + (end - start) + " ms");

        PerformanceTest.CompressionRatio(out,in);
    }

    public Decoder(File in, File out) throws FileNotFoundException {
        if(in == null || out == null){
            throw new NullPointerException();
        }
        this.in = new ByteInputStream(new FileInputStream(in),1024);
        this.out = new BufferedOutputStream(new FileOutputStream(out),1024);
        this.huffman = new HuffmanTree();
    }

    public void decode() throws IOException {
        Map<Integer, String> codeMap = parseCodeMap();
        huffman.createTree(codeMap);
        decodeFile();
        close();
    }

    private Map<Integer, String> parseCodeMap() throws IOException {
        // 0x0000 save the number of symbols
        int size = in.read();
        size <<= 8;
        size |= in.read();
        // after 2*size save the symbol and the length of its huffmanCode
        int[] bytes = in.read(size*2);
        // save the code of symbol
        Map<Integer, String> leafCode = new HashMap<>(size);
        for(int i=0; i< size; i++){
             Integer val = bytes[i*2];
             String code = in.readBits(bytes[i*2+1]);
             leafCode.put(val,code);
        }
        // empty the buffer
        in.empty();
        return leafCode;
    }


    public  void decodeFile() throws IOException {
        int count = 1;
        Node proxy = huffman.getRoot();
        int bit = -1;
        while ((bit = in.readBit()) != -1){
            proxy = bit == 0 ? proxy.getLeftChild() : proxy.getRightChild();
            if(huffman.isLeafNode(proxy)){
                count++;
                out.write(proxy.getValue());
                proxy = huffman.getRoot();
            }
        }
    }

    private void close() throws IOException {
        in.close();
        out.close();
    }


}
