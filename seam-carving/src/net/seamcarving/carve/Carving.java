package net.seamcarving.carve;

public class Carving {
    public static int[][] pathFind(Img img) {
        /*
        this function is finding darkest pixel on
        top then it's looking for darkest neighbours
        to the bottom.

        (not effective for seam carving, but fast)
        */

        int[] current = {darkestPositionX(img, 0), 0}; // [0] - x; [1] - y
        int[][] path = new int[img.width][img.height]; //contains x and y of darkest pixels

        while(current[1]!=img.height-1) {
            path[current[1]][0] = current[0];
            path[current[1]][1] = current[1];
            current[0] = darkestNeighbour(img, current);
            current[1]++;
        }



        return path;
    }
    /*

    public static int[][] pathFindCPUFuck(Img img) {
        //TODO bruteforce
        return null;
    }
    */
    public static void pathDraw(Img img, int[][] path) {
        for (int[] ints : path) {
            img.result.setRGB(ints[0], ints[1], -65536); //-65536 is red
        }
    }

    public static int darkestPositionX(Img img, int y) {
        int darkest=255, darkestPosition=0, currentColorBlue;

        for (int x = 0; x<img.width; x++) {
            currentColorBlue = img.rgbColor(x, y).getBlue();
            if(currentColorBlue<darkest) { //actually you can use getGreen or getRed but I recommend getBlue
                darkest = currentColorBlue;
                darkestPosition = x;
            }
        }
        return darkestPosition;
    }

    public static int darkestNeighbour(Img img, int[] position) {
        //position contains [0]=x [1]=y

        /*
        * position is always on top.
        *
        *         X <-position       (X is pixel)
        *       X X X <-neighbours
        *
        * neighbour Y is equal to position
        * y-1
        *
        * neighbour X is equal to position
        * middle -1, +0 or +1
        */
        int darkest = 255;
        int darkestPosition = 0;

        int currentColorBlue, x;

        for (byte i = -1; i < 1; i++) {
            x = position[0] + i + 1;

            currentColorBlue = img.rgbColor(x, position[1]).getBlue();

            if(currentColorBlue==0) return x;
            if(darkest>currentColorBlue) {
                darkest = currentColorBlue;
                darkestPosition = x;
            }
        }
        return darkestPosition;
    }

}
