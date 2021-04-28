package net.seamcarving.carve;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        /*
        if(args.length!=2) {
            System.out.println("Usage: filter.jar [INPUT].jpg [OUTPUT].jpg");
            System.exit(1);
        }
        Img img = new Img(args[0]);
        Img output = new Img(args[1]);

        */
        Img img = new Img("eiffel.jpg");
        Img output = new Img("eiffel2.jpg");

        for (int x = 0; x<img.width; x++){
            for(int y = 0; y<img.height; y++){

                Color color1 = img.rgbColor(x, y);
                Color color2 = img.rgbColor(x, y);

                if (x != 0) color1 = img.rgbColor(x-1, y);
                if (x != img.width-1) color2 = img.rgbColor(x+1, y);
                //Color newColor = new Color(r, g, b);

                //float changed to int
                int r = color1.getRed() - color2.getRed();
                int g = color1.getGreen() - color2.getGreen();
                int b = color1.getBlue() - color2.getBlue();

                img.bimg.setRGB(x, y, r+g+b);
            }
        }
        ImageIO.write(img.bimg, output.extension, output.file);
        System.out.println("Successfully wrote to "+output.imagePath);
    }
}