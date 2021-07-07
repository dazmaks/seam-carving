package net.seamcarving.carve;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Img img = new Img("waterfall.jpg");
        Img temp = new Img("waterfall-temp.jpg");
        Img output = new Img("waterfall-result.jpg");

        Energy.toEnergy(img);
        ImageIO.write(img.result, img.extension, temp.file); //writing result to file

        int [][] path = Carving.pathFind(img);
        Carving.pathDraw(img, path);
        ImageIO.write(img.result, img.extension, output.file); //writing result to file
        System.out.println("Successfully wrote to "+output.imagePath);
    }
}
