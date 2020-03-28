package huffman;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * @author Wait
 */
public class Encoder {
    /** create the binary tree and get the leaf code */
    private HuffmanTree huffman;

    /** buffered input stream to read the decompressed file*/
    private BufferedInputStream in;

    /** output stream to write the compressed file*/
    private ByteOutStream out;

    /** save the input file's path*/
    private String  inputPath;

    public static void main(String[] args) throws IOException {
        File in = new File("resources/text2");
        File out =new File("resources/mid.txt");
        Encoder encoder = new Encoder(in, out);

        long start = System.nanoTime();
        encoder.encode();
        long end = System.nanoTime();

        System.out.println("Compression time:  " + (float)(end - start)/1000000 + " ms");
        PerformanceTest.CompressionRatio(in,out);
    }


    public Encoder(File inFile, File outFile) throws IOException {
        if(inFile == null || outFile == null){
            throw new NullPointerException();
        }
        this.in = new BufferedInputStream(new FileInputStream(inFile),1024);
        this.out = new ByteOutStream(new FileOutputStream(outFile));
        this.inputPath = inFile.getPath();
        this.huffman = new HuffmanTree();
    }

    public void  encode() throws IOException {
        int[] map = statistic();
        huffman.loadNodes(map);
        huffman.createTree();
        Map<Integer, String> leafCode = huffman.getAllLeafCode();
        saveCodeMap(leafCode);
        reopenInput();
        encodeFile(leafCode);
        close();
    }

    private void saveCodeMap(Map<Integer, String> leafCode) throws IOException {
        Set<Integer> keySet = leafCode.keySet();
        // 0x0000-0x0001: write the number of symbols, assume the size = n
        int size = keySet.size();
        out.writeByte(size>>8 & 0xff);
        out.writeByte(size & 0xff);
        // after 2n bytes save the symbol and its code length
        for(Integer  val : keySet ){
            out.writeByte(val);
            int len = leafCode.get(val).length();
            out.writeByte(len);

        }
        // write their code
        for(Integer  val : keySet ){
            String s = leafCode.get(val);
            out.writeBit(s);
            System.out.println((char)val.intValue() + " : " + s );
        }
        out.bitFlush();
    }


    /**
     * using the leaf-code map to encode the input file and save to output file
     * @throws IOException
     */
    private void encodeFile(Map<Integer, String> leafMap) throws IOException {
        out.empty();
        int val = 0;
        while ((val = in.read()) != -1){
            String code = leafMap.get(val);
            out.writeBit(code);
        }
        out.bitFlush();
    }

    /**
     * make use of invoke, execute the FileInputStream's method "open0" that is private.
     * restart to read the file to encode this file.
     */
    private void reopenInput() throws FileNotFoundException {
        in =new  BufferedInputStream(new FileInputStream(inputPath));
//        try {
//            Method open0 = in.getClass().getDeclaredMethod("", String.class);
//            open0.setAccessible(true);
//            open0.invoke(in,this.inputPath);
//        }
//        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * statistic the file's all symbol and their frequencies
     * @return  Map: key is symbol and value is frequency
     * @throws IOException
     */
    private int[] statistic() throws IOException {
        int[] array = new int[256];
        int val = 0;
        while ((val = in.read()) != -1){
            array[val]++;
        }
        in.close();
        return array;
    }

    /**
     * close the stream and release resource.
     * @throws IOException
     */
    private void close() throws IOException {
        in.close();
        out.close();
    }



}
