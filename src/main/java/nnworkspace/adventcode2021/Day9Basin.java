package nnworkspace.adventcode2021;

import java.util.*;
import java.util.logging.Logger;

/** https://adventofcode.com/2021/day/9 */
public class Day9Basin {
  private static final Logger logger = Logger.getLogger(Day9Basin.class.getName());

  int[][] heightMap = null;
  int rows = 0;
  int cols = 0;

  public Day9Basin(String fileName) {
    this.heightMap = PuzzlesUtil.parseMatrix(fileName, true);
    this.rows = heightMap.length;
    this.cols = heightMap[0].length;
  }

  public int calc() {
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
          lowPointsSum += (self + 1);
        }
      }
    }

    return lowPointsSum;
  }

  public int calc2() {

    List<Set<String>> basins = new ArrayList<>();

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {

        int self = heightMap[row][col];

        String leftPos = String.format("(%d, %d)", row, col - 1);
        String upperPos = String.format("(%d, %d)", row - 1, col);
        String selfPos = String.format("(%d, %d)", row, col);

        if (self < 9) {

          // check if left cell already in basin
          Set<String> basinLeft = adjToLeft(basins, leftPos, selfPos);

          // check if upper cell already in basin
          Set<String> basinUpper = adjToUpper(basins, upperPos, selfPos);

          if (basinLeft == null || basinUpper == null) {
            Set<String> newBasin = new HashSet<>();
            newBasin.add(selfPos);
            basins.add(newBasin);
          }

          if (basinLeft != null && basinUpper != null && !basinLeft.equals(basinUpper)) {
            // in this case, merge the basins
            Set<String> mergedBasin = new HashSet<>();
            mergedBasin.addAll(basinLeft);
            mergedBasin.addAll(basinUpper);
            basins.remove(basinLeft);
            basins.remove(basinUpper);
            basins.add(mergedBasin);
          }
        }
      }
    }

    List<Integer> areas = basins.stream().map(Set::size).sorted(Comparator.reverseOrder()).toList();
    int a0 = areas.get(0);
    int a1 = areas.get(1);
    int a2 = areas.get(2);

    logger.info(String.format("Areas of biggest 3 basins are: %d, %d, %d", a0, a1, a2));

    return a0 * a1 * a2;
  }

  private Set<String> adjToUpper(List<Set<String>> basins, String upperPos, String selfPos) {

    for (Set<String> basin : basins) {
      if (basin.contains(upperPos)) {
        basin.add(selfPos);
        return basin;
      }
    }

    return null;
  }

  private Set<String> adjToLeft(List<Set<String>> basins, String leftPos, String selfPos) {
    for (Set<String> basin : basins) {
      if (basin.contains(leftPos)) {
        basin.add(selfPos);
        return basin;
      }
    }

    return null;
  }
}
