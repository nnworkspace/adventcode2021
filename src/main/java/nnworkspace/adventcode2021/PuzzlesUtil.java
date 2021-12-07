package nnworkspace.adventcode2021;

import com.google.common.collect.ImmutableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PuzzlesUtil {

    public static ImmutableList<Integer> parseIntsInFirstLine(String configFile) {
        ImmutableList<Integer> integers = null;
        try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
            String firstLine = br.readLine();
            integers = ImmutableList.copyOf(
                            Arrays.stream(firstLine.split(","))
                                    .map(intStr -> Integer.parseInt(intStr.trim()))
                                    .toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return integers;
    }

    public static void printMatrix(int[][] matrix) {
        for (int y = 0; y < matrix[0].length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                System.out.print(matrix[x][y] + " ");
            }
            System.out.println();
        }
    }
}
