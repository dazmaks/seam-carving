package net.seamcarving.carve;

import javax.imageio.ImageIO;
import java.io.IOException;

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
        Img img = new Img("cat.jpg");
        Img output = new Img("cat-result.jpg");

        Energy.toEnergy(img);
        ImageIO.write(img.result, img.extension, output.file); //writing result to file
        System.out.println("Successfully wrote to "+output.imagePath);
    }
}
