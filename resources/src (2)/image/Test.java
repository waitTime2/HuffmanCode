package image;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Wait
 */
public class Test {
    private static int average(int rgb){
        int l = rgb>>24 & 0xff;
        int r = rgb>>16 & 0xff;
        int g = rgb>>8 & 0xff;
        int b = rgb & 0xff;
        int v = (r + g + b)/3;
        return (l<<24) | v<<16 | v<<8 | v;
    }

    private static int weighting(int rgb){
        int L = rgb>>24 & 0xff;
        int R = rgb>>16 & 0xff;
        int G = rgb>>8 & 0xff;
        int B = rgb & 0xff;
        int v = (int)(0.30*R + 0.59*G + 0.11*B);
        return (L<<24) | v<<16 | v<<8 | v;
    }


    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("resources/girl8.bmp");
        BufferedImage image = ImageIO.read(in);
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        for(int i=0; i<width; i++){
            for(int j = 0; j < height; j++){
                int val = Test.weighting(image.getRGB(i,j));
                grayImage.setRGB(i,j,val);
            }
        }
        ImageIO.write(grayImage,"bmp",new File("resources/p2.bmp"));

    }
}
