package image;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Wait
 */
public class ParseBmp {
    private FileInputStream in;
    private int size;
    private int offset;
    private int width;
    private int height;

    public ParseBmp(FileInputStream in) throws IOException {
        this.in = in;
        parse();
    }

    public void parse() throws IOException {
        //parse the header of BMP file
        parseHeader();
        parseBaseInfo();
    }

    /**
     * the header of BMP have the 14 bytes.
     * 00-01: BM (type flag)
     * 02-05: the size of file
     * 06-0A: retain(default zero)
     * 0B-0E: the offset between the first byte and the fist byte of pixel-data.
     * @throws IOException
     */
    public void parseHeader() throws IOException {
        skipBytes(2);
        this.size = readInt();
        skipBytes(4);
        this.offset = this.readInt();
    }

    public void parseBaseInfo() throws IOException {
        skipBytes(4);
        this.width = readInt();
        this.height = readInt();
    }
    /**
     * Little-endian
     * @return int
     * @throws IOException
     */
    private  int readInt() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        int ch3 = in.read();
        int ch4 = in.read();
        return (ch1 | ch2 << 8 | ch3 << 16 | ch4 << 24);
    }

    private void skipBytes(int count) throws IOException {
        long m = in.skip(count);
    }


    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("resources/girl8.bmp");
        ParseBmp parseBmp = new ParseBmp(in);

    }
}
