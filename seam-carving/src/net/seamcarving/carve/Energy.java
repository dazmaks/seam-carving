package net.seamcarving.carve;

import java.awt.*;

import static java.lang.StrictMath.sqrt;

public class Energy {
    public static void energy(Img img) {
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

                Color ensq = new Color((int)sqrt(r*r + g*g + b*b)); //calculating energy
                int midensq = ensq.getRed() + ensq.getBlue() + ensq.getGreen(); //calculating arithmetic mean

                Color newColor = new Color(midensq, midensq, midensq); //making image mono-chrome

                img.result.setRGB(x, y, newColor.getRGB()); //setting pixel
            }
        }
    }
}
