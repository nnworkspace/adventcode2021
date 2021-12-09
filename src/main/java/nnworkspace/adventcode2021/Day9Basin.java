package nnworkspace.adventcode2021;

import java.util.Arrays;
import java.util.logging.Logger;

public class Day9Basin {
  private static final Logger logger = Logger.getLogger(Day9Basin.class.getName());

  private String fileName = null;

  public Day9Basin(String fileName) {
    this.fileName = fileName;
  }

  public int calc() {

    int[][] heightMap = PuzzlesUtil.parseMatrix(fileName, true);
    int rows = heightMap.length;
    int cols = heightMap[0].length;

    // List<Integer> lowPoints = new ArrayList<>();
    int lowPointsSum = 0;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        int left = col == 0 ? 999 : heightMap[row][col - 1];
        int upper = row == 0 ? 999 : heightMap[row - 1][col];
        int self = heightMap[row][col];
        int right = col == (cols - 1) ? 999 : heightMap[row][col + 1];
        int below = row == (rows - 1) ? 999 : heightMap[row + 1][col];
        int[] adjacents = new int[] {left, upper, self, right, below};
        Arrays.sort(adjacents);
        if (self == adjacents[0] && self < adjacents[1]) {
          // lowPoints.add(self);
          lowPointsSum += (self + 1);
        }
      }
    }

    return lowPointsSum;
  }

  public int calc2() {
    return 0;
  }
}
