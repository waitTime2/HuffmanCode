package huffman;

import java.io.*;

/**
 * @author Wait
 */
public class ByteOutStream extends BufferedOutputStream {
    /** the max value of leftCount */
    private final int MAX_COUNT = 8;

    /** buffer of bits is to write to output */
    private int bitBuffer = 0;

    /** record the size of bitBuffer.  */
    private int leftCount = 0;

    public ByteOutStream(OutputStream out) {
        super(out);
    }

    public ByteOutStream(OutputStream out, int size) {
        super(out, size);
    }


    /**
     * the buffer of bits append a bit
     * if bit is '0', appending 0;
     * if bit is '1', appending 1;
     * else thinking that it is error input.
     * @param bit
     * @throws IOException
     */
    public void writeBit(int bit) throws IOException {
        if(leftCount >= MAX_COUNT){
            bitFlush();
        }
        // append a bit
        bitBuffer <<= 1;
        bitBuffer |= bit;

        //update the bitBuffer's size
        leftCount++;


    }

    /**
     * write the binary string
     * @param binaryString   the bits only include '1' or '0'
     * @throws IOException
     */
    public void writeBit(String binaryString) throws IOException {
        char[] bits = binaryString.toCharArray();
        for(char bit : bits ){
            writeBit(bit-'0');
        }
    }

    public  void writeByte(int b) throws IOException {
        if(leftCount == 0){
            // the buffer is empty, so just write the value
            super.write(b);
        }
        else{
            // write the byte by bit
            for(int i=0 ;i < MAX_COUNT; i ++){
                int bit = (b>>>(7-i) & 1) ;
                writeBit(bit);
             }
        }

    }


    public  void bitFlush() throws IOException {
        if(leftCount == 0) {
            return;
        }
        else if (leftCount < 8){
            // make the buffer fill
            bitBuffer <<= (MAX_COUNT - leftCount);
        }
        super.write(bitBuffer);
        // empty the buffer
        empty();

    }

    /**
     * reset the buffer and restart next write.
     */
    public void empty(){
        leftCount = 0;
        bitBuffer = 0;
    }

}
