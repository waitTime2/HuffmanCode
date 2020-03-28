package runlength;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Wait
 */
public class Encoder {

    private FileInputStream in;

    public static void main(String[] args) throws IOException {
        Encoder encoder = new Encoder(new FileInputStream("resources/text2"));
        encoder.encode();
    }

    public Encoder(FileInputStream in) {
        this.in = in;
    }

    public void encode() throws IOException {
        int key = in.read();
        System.out.print((char)key);
        int count = 1;
        int b = -1;
        while((b = in.read()) != -1){
            if(key == b){
                count++;
            }else{
                key = b;
                System.out.print(count);
                System.out.print((char)b);
                count = 1;
            }
        }
        System.out.println(count);
    }

    public boolean containKey(int[] keys, int b){
        for(int key : keys){
            if(key == b){
                return true;
            }
        }
        return false;
    }
}
