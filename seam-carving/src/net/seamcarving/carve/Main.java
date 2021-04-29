package net.seamcarving.carve;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        if(args.length!=2) {
            System.out.println("Usage: filter.jar [INPUT].jpg [OUTPUT].jpg");
            System.exit(1);
        }
        Img img = new Img(args[0]);
        Img output = new Img(args[1]);

        */
        Img img = new Img("ducks.jpg");
        Img output = new Img("ducks-result.jpg");

        for (int x = 0; x<img.width; x++){
            for(int y = 0; y<img.height; y++){
                //getting current pixel color
                Color color1 = img.rgbColor(x, y);
                Color color2 = img.rgbColor(x, y);

                //out of bounds
                if (x != 0) color1 = img.rgbColor(x-1, y);
                if (x != img.width-1) color2 = img.rgbColor(x+1, y);

                int r = color1.getRed() - color2.getRed();
                int g = color1.getGreen() - color2.getGreen();
                int b = color1.getBlue() - color2.getBlue();

                Color ensq = new Color((int)sqrt(pow(r + g + b, 2))); //calculating energy
                int midensq = (int) ((ensq.getRed() + ensq.getBlue() + ensq.getGreen())/1.5); //calculating arithmetic mean

                Color newColor = new Color(midensq, midensq, midensq); //making image mono-chrome

                img.result.setRGB(x, y, newColor.getRGB()); //setting pixel
            }
        }

        ImageIO.write(img.result, img.extension, output.file); //writing result to file
        System.out.println("Successfully wrote to "+output.imagePath);
    }
}
