package net.seamcarving.carve;

import java.awt.*;

import static java.lang.StrictMath.sqrt;

public class Energy {
    public static void energy(Img img) {
        Color defaultColor;
        Color color1, color2, color3, color4;

        int r, g, b;

        for (int x = 0; x<img.width; x++){
            for(int y = 0; y<img.height; y++){
                //getting current pixel color
                defaultColor = img.rgbColor(x, y);
                color1 = defaultColor;
                color2 = defaultColor;
                color3 = defaultColor;
                color4 = defaultColor;

                //out of bounds
                if (x != 0) color1 = img.rgbColor(x-1, y);
                if (x != img.width-1) color2 = img.rgbColor(x+1, y);

                r = (color1.getRed() - color2.getRed() + color3.getRed() - color4.getRed())/2;
                g = (color1.getGreen() - color2.getGreen() + color3.getGreen() - color4.getGreen())/2;
                b = (color1.getBlue() - color2.getBlue() + color3.getBlue() - color4.getBlue())/2;

                Color ensq = new Color((int)sqrt(r*r + g*g + b*b)); //calculating energy
                int midensq = ensq.getRed() + ensq.getBlue() + ensq.getGreen(); //calculating arithmetic mean

                Color newColor = new Color(midensq, midensq, midensq); //making image mono-chrome

                img.result.setRGB(x, y, newColor.getRGB()); //setting pixel
            }
        }
    }
}
