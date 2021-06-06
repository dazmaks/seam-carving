package net.seamcarving.carve;

import java.awt.*;

import static java.lang.StrictMath.sqrt;

public class Energy {
    public static void toEnergy(Img img) {
        Color defaultColor;
        Color color1, color2;

        int r, g, b;

        int energyAM;

        //x is width
        //y is height
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

    public static void toEnergyDarker(Img img) {
        Color defaultColor;
        Color color1, color2;

        int c1, c2, c3;

        int r, g, b;
        int smallest = 0;
        int smallest2;
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

                if (y != 0){
                    c1 = new Color(img.result.getRGB(x, y-1)).getBlue();

                    if ((x != 0) && (x != img.width-1))  {
                        c2 = new Color(img.result.getRGB(x-1, y-1)).getBlue();
                        c3 = new Color(img.result.getRGB(x+1, y-1)).getBlue();
                        smallest = Math.min(c1, Math.min(c2, c3));
                    } else if (x == 0) {
                        c3 = new Color(img.result.getRGB(x+1, y-1)).getBlue();
                        smallest = Math.min(c1, c3);
                    } else if (x == img.width-1) {
                        c2 = new Color(img.result.getRGB(x-1, y-1)).getBlue();
                        smallest = Math.min(c1, c2);
                    }
                    smallest2 = (smallest + energyAM)/2;

                    Color newColor2 = new Color(smallest2, smallest2, smallest2);

                    img.result.setRGB(x, y, newColor2.getRGB());
                }
            }
        }
    }
}
