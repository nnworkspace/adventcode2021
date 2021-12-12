package nnworkspace.adventcode2021;

import com.google.common.collect.ImmutableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PuzzlesUtil {

  public static Stream<String> readAllLines(String relativePath) {
    Stream<String> stream = null;

    try {
      stream =
          Files.lines(
              Paths.get(
                  Objects.requireNonNull(PuzzlesUtil.class.getResource(relativePath)).toURI()));
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    assert stream != null;

    return stream;
  }

  public static ImmutableList<String> getAllLines(String fileName) {
    List<String> lines = null;
    try {
      lines =
          Files.lines(
                  Paths.get(
                      Objects.requireNonNull(PuzzlesUtil.class.getResource(fileName)).toURI()))
              .map(String::trim)
              .toList();
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    return ImmutableList.copyOf(lines);
  }

  public static ImmutableList<Integer> parseIntsInFirstLine(String configFile) {
    ImmutableList<Integer> integers = null;
    try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
      String firstLine = br.readLine();
      integers =
          ImmutableList.copyOf(
              Arrays.stream(firstLine.split(","))
                  .map(intStr -> Integer.parseInt(intStr.trim()))
                  .toList());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return integers;
  }

  /**
   * A matrix like this
   *
   * <p>2199943210 3987894921 9856789892 8767896789 9899965678
   *
   * @param fileName
   * @return
   */
  public static int[][] parseMatrix(String fileName, boolean hasReturnAtLastLine) {
    // first, get the row numbers and col numbers
    int rows = getLineCounts(fileName, hasReturnAtLastLine);
    int[][] matrix = new int[rows][];
    int row = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      for (String line; (line = br.readLine()) != null; ) {
        line = line.trim();
        int cols = line.length();
        matrix[row] = new int[cols];
        for (int i = 0; i < cols; i++) {
          matrix[row][i] = Character.getNumericValue(line.charAt(i));
        }
        ++row;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return matrix;
  }

  public static int getLineCounts(String fileName, boolean hasReturnAtLastLine) {
    int result = 0;
    try (FileReader input = new FileReader(fileName);
        LineNumberReader count = new LineNumberReader(input); ) {
      while (count.skip(Long.MAX_VALUE) > 0) {
        // Loop just in case the file is > Long.MAX_VALUE or skip() decides to not read the entire
        // file
      }

      result = count.getLineNumber() + 1; // +1 because line index starts at 0
    } catch (IOException e) {
      e.printStackTrace();
    }

    return hasReturnAtLastLine ? result - 1 : result;
  }

  public static void printMatrix(int[][] matrix) {
    for (int y = 0; y < matrix[0].length; y++) {
      for (int x = 0; x < matrix.length; x++) {
        System.out.print(matrix[x][y] + " ");
      }
      System.out.println();
    }
  }

  public static int[][] cloneMatrixInt(int[][] matrix) {
    int[][] nm = new int[matrix.length][matrix[0].length];
    for (int i = 0; i < nm.length; i++) {
      nm[i] = Arrays.copyOf(matrix[i], matrix[i].length);
    }

    return nm;
  }

  public static int[][] initMatrixTo(int matrixWidth, int matrixHeight, int initValue) {
    int[][] nm = new int[matrixHeight][matrixWidth];
    for (int row = 0; row < matrixHeight; row++) {
      for (int col = 0; col < matrixWidth; col++) {
        nm[row][col] = initValue;
      }
    }

    return nm;
  }

  public static boolean[][] initMatrixTo(int matrixWidth, int matrixHeight, boolean initValue) {
    boolean[][] nm = new boolean[matrixHeight][matrixWidth];
    for (int row = 0; row < matrixHeight; row++) {
      for (int col = 0; col < matrixWidth; col++) {
        nm[row][col] = initValue;
      }
    }

    return nm;
  }
}
