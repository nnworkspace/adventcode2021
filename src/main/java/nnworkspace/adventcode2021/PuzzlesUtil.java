package nnworkspace.adventcode2021;

public class PuzzlesUtil {

    public static void printMatrix(int[][] matrix) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                System.out.print(matrix[x][y] + " ");
            }
            System.out.println();
        }
    }
}
