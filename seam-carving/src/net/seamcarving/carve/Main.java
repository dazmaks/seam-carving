package net.seamcarving.carve;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Img img = new Img("falls.jpg");
        Img temp = new Img("falls-temp.jpg");
        Img output = new Img("falls-result.jpg");

        Energy.toEnergy(img);
        ImageIO.write(img.result, img.extension, temp.file); //writing result to file

        int [][] path = Carving.pathFind(img);
        Carving.pathDraw(img, path);
        ImageIO.write(img.result, img.extension, output.file); //writing result to file
        System.out.println("Successfully wrote to "+output.imagePath);
    }
}
