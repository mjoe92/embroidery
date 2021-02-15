package com.codecool.embroidery;

public class Embroidery {
    public static void main(String[] args) {

/*        printShape(drawRectangle(20,8,2,0,3));
        System.lineSeparator();
        printShape(drawTriangle(7,1,2));
       System.lineSeparator();*/
//        printShape(drawChristmasTree(3, 1,2));
/*        System.lineSeparator();*/
        printShape(drawCircle(15,1,2));
    }


    private static int[][] drawRectangle(int width, int height, Integer borderColor, Integer fillColor, Integer borderWidth) {

        int[][] rectValues = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rectValues[i][j] = borderColor;
            }
        }
        for (int i = borderWidth; i < height - borderWidth; i++) {
            for (int j = borderWidth; j < width - borderWidth; j++) {
                rectValues[i][j] = fillColor;
            }
        }
        return rectValues;
    }

    private static int[][] drawTriangle(int height, Integer borderColor, Integer fillColor) {

        int[][] triValues = new int[height][height * 2 - 1];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height * 2 - 1; j++) {
                triValues[i][j] = 0;
            }
        }

        int k = 0;
        for (int i = height - 1; 0 <= i; i--) {
            for (int j = k; j < height * 2 - 1 - k; j++) {
                triValues[i][j] = borderColor;
            }
            k++;
        }

        k = 1;
        for (int i = height - 1 - k; 1 <= i; i--) {
            for (int j = k + 1; j < height * 2 - 2 - k; j++) {
                triValues[i][j] = fillColor;
            }
            k++;
        }

        return triValues;
    }

    private static int[][] drawChristmasTree(int blocks, Integer borderColor, Integer fillColor) {

        int blockHeight = 3;
        int height = blockHeight * blocks;
        int width = blockHeight * blocks + (blockHeight - blocks);
        int[][] treeValues = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                treeValues[i][j] = 0;
            }
        }

        int blockSteps;
        for (int l = 0; l < blocks; l++) {
            int k = 0;
            blockSteps = l * blockHeight;

            for (int i = height - 1 - blockSteps; 0 <= i; i--) {
                for (int j = k + l; j < width - k - l; j++) {
                    treeValues[i][j] = borderColor;
                }
                k++;
            }

        }

        for (int l = 0; l < blocks; l++) {
            int k = 0;
            blockSteps = l * blockHeight;

            for (int i = height - 1 - blockSteps; 0 <= i; i--) {
                for (int j = k + l + 1; j < width - k - l - 1; j++) {
                    if (i == height - 1) break;
                    treeValues[i][j] = fillColor;
                }
                k++;
            }
        }
        return treeValues;
    }

    private static int[][] drawCircle(int radius, Integer borderColor, Integer fillColor) {
        // r=10, b=1, f=2
        int height = 2 * radius + 1;
        int width = height;
        int[][] circleValues = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                circleValues[i][j] = fillColor;
            }
        }

        // right-bottom arc
        int k;
        for (int i = height - 1; radius <= i; i--) {
            double sqrSum = Math.pow(radius, 2) - Math.pow(i / 2, 2);
            double radiusInDouble = radius;
            k = radius + (int) Math.round(Math.sqrt(sqrSum) + Math.sqrt(radiusInDouble));
            for (int j = k; j < width; j++) {
                circleValues[i][j] = 0;
            }
        }

        // for bordering
        for (int i = radius; i < height - 1; i++) {
            for (int j = radius; j < width - 1; j++) {
                if (circleValues[i][j] == fillColor && circleValues[i][j + 1] == 0) {
                    circleValues[i][j] = borderColor;
                }
                if (circleValues[i][j] == fillColor && circleValues[i + 1][j] == 0) {
                    circleValues[i][j] = borderColor;
                }
            }
        }
        // bordering the edges
        for (int i = radius; i < height; i++){
            circleValues[height - 1][i] = (circleValues[height - 1][i] == 2 ? 1 : 0);
        }
        for (int i = radius; i < height; i++){
            circleValues[i][height - 1] = (circleValues[i][height - 1] == 2 ? 1 : 0);
        }

        // mirror to rop arc
        for (int i = 0; i <= radius; i++) {
            circleValues[i] = circleValues[height - i - 1];
        }

        // mirror ro left half
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < radius; j++) {
                circleValues[i][j] = circleValues[i][height - j - 1];
            }
        }
        return circleValues;
    }

    private static void printShape(int[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                System.out.print(shape[i][j] + " ");
            }
            System.out.println();
        }
    }
}
