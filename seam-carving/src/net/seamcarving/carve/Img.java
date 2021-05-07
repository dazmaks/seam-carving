package net.seamcarving.carve;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Img {
    //tested on jpg and png
    public BufferedImage getBImg(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getExtension() {

        int i = this.imagePath.lastIndexOf('.');
        if (i > 0) {
            return this.imagePath.substring(i+1);
        }else{
            return null;
        }
    }

    String imagePath, extension;
    int height, width;
    BufferedImage bufferedImage, result;
    File file;
    boolean exists;

    public Img(String imagePath) {
        this.imagePath = imagePath;
        this.file = new File(imagePath);
        this.exists = file.exists();
        if(exists) {
            this.bufferedImage = getBImg(this.file);
            this.height = this.bufferedImage.getHeight();
            this.width = this.bufferedImage.getWidth();
            this.result = new BufferedImage(this.width, this.height, this.bufferedImage.getType());
        }else{
            this.bufferedImage = null;
            this.height = 0;
            this.width = 0;
            this.result = null;
        }
        this.extension = getExtension();
    }

    public Color rgbColor(int x, int y) {
        return new Color(this.bufferedImage.getRGB(x, y));
    }
}
