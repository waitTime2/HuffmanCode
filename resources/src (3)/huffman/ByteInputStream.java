package huffman;

import java.io.*;


/**
 * @author Wait
 */
public class ByteInputStream extends BufferedInputStream  {


    private int bitBuffer = 0;

    private int next = 0 ;


    private boolean endRead = false;

    public ByteInputStream(InputStream in) {
        super(in);
    }

    public ByteInputStream(InputStream in, int size) {
        super(in, size);
    }



    /**
     * read a bit
     * @return int 1/0
     */
    public int readBit() throws IOException {
        if(next == 0 ){
            bitBuffer = super.read();
            if(bitBuffer == -1){
                return -1;
            }
            next = 8;
        }
        next --;
        return (bitBuffer >>next) & 0x01;
    }

    /**
     * read bits
     * @param size the number of bits
     * @return the binaryString
     */
    public String readBits(int size) throws IOException {
       StringBuilder builder = new StringBuilder();
       for(int i=0; i< size; i++){
           builder.append(readBit());
       }
       return builder.toString();
    }



    /**
     * read bytes that its len is count.
     *
     * @param size the number of read a time.
     * @return int[] bytes
     * @throws IOException
     */
    public int[] read(int size) throws IOException {
        int[] bytes = new int[size];
        for(int i= 0; i<size; i++){
            bytes[i] = read();
        }
        return bytes;
    }







    public void empty(){
        bitBuffer = 0;
        next = 0;
    }
}
