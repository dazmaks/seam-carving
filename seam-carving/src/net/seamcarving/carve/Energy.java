package net.seamcarving.carve;

import java.awt.*;

import static java.lang.StrictMath.sqrt;

public class Energy {
    public static void toEnergy(Img img) {
        Color defaultColor;
        Color color1, color2;

        int r, g, b;

        int energyAM;

        for (int x = 0; x<img.width; x++){
            for(int y = 0; y<img.height; y++){
                //getting current pixel color
                defaultColor = img.rgbColor(x, y);
                color1 = defaultColor;
                color2 = defaultColor;

                //out of bounds
                if (x != 0) color1 = img.rgbColor(x-1, y);
                if (x != img.width-1) color2 = img.rgbColor(x+1, y);

                //actually image become sharper after dividing by 2
                r = (color1.getRed() - color2.getRed())/2;
                g = (color1.getGreen() - color2.getGreen())/2;
                b = (color1.getBlue() - color2.getBlue())/2;

                Color energy = new Color((int)sqrt(r*r + g*g + b*b)); //calculating energy
                energyAM = energy.getRed() + energy.getBlue() + energy.getGreen(); //calculating arithmetic mean

                Color newColor = new Color(energyAM, energyAM, energyAM); //making image mono-chrome

                img.result.setRGB(x, y, newColor.getRGB()); //setting pixel
            }
        }
    }
}
