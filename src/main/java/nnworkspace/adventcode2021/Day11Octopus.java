package nnworkspace.adventcode2021;

import java.util.Arrays;
import java.util.logging.Logger;

/** https://adventofcode.com/2021/day/11 */
public class Day11Octopus {
  private static final Logger logger = Logger.getLogger(Day11Octopus.class.getName());

  private int[][] oMatrix;

  private int matrixWidth = 0;
  private int matrixHeight = 0;

  public Day11Octopus(String fileName) {
    this.oMatrix = PuzzlesUtil.parseMatrix(fileName, true);

    this.matrixHeight = oMatrix.length;
    this.matrixWidth = oMatrix[0].length;
  }

  public int calc(int steps) {

    int flashSum = 0;

    int[][] matrix = PuzzlesUtil.cloneMatrixInt(oMatrix);
    for (int i = 1; i <= steps; i++) {
      matrix = runOneStep(matrix);

      // count zeros
      flashSum += sumFlashesInOneStep(matrix);
    }

    return flashSum;
  }

  public int calc2() {
    int[][] matrix = PuzzlesUtil.cloneMatrixInt(oMatrix);

    int steps = 0;
    boolean stop = false;
    while (!stop) {

      ++steps;
      matrix = runOneStep(matrix);

      stop = isAllFlash(matrix);
    }

    return steps;
  }

  public int[][] runOneStep(int[][] matrix) {

    int[][] prevMatrix = PuzzlesUtil.cloneMatrixInt(matrix);
    int[][] currMatrix = PuzzlesUtil.cloneMatrixInt(prevMatrix);

    boolean[][] alreadyFlashed = PuzzlesUtil.initMatrixTo(matrixWidth, matrixHeight, false);

    int iters = 0;

    // State of the matrix will change when the flashing rules propagate.
    // When the state of the matrix stays still, all rules are propagated to its furthest possibility
    // stop and return the state of the matrix as the result of this step
    while (iters == 0 || !Arrays.deepEquals(prevMatrix, currMatrix)) {

      for (int row = 0; row < matrixHeight; row++) {
        for (int col = 0; col < matrixWidth; col++) {
          int eLevel = currMatrix[row][col];

          prevMatrix[row][col] = eLevel;

          eLevel = increaseByOneOrDoNothing(alreadyFlashed, iters, row, col, eLevel);

          eLevel = applyFlashRules(currMatrix, alreadyFlashed, row, col, eLevel);

          currMatrix[row][col] = eLevel;
        }
      }

      ++iters;
    }

    return currMatrix;
  }

  private int increaseByOneOrDoNothing(boolean[][] alreadyFlashed, int iters, int row, int col, int eLevel) {
    if (eLevel == 0 && alreadyFlashed[row][col]) {
      // do nothing
    } else {
      if (iters == 0) {
        // increment by 1 happens only once per step
        // that's why the condition iters == 0
        ++eLevel;
      }
    }
    return eLevel;
  }

  private int applyFlashRules(int[][] currMatrix, boolean[][] alreadyFlashed, int row, int col, int eLevel) {
    if (eLevel > 9 && !alreadyFlashed[row][col]) {
      // reset self to 0
      eLevel = 0;

      // increase neighbouring cells
      increaseNeighbours(currMatrix, row, col, alreadyFlashed);

      alreadyFlashed[row][col] = true;
    }
    return eLevel;
  }

  private void increaseNeighbours(
      int[][] currMatrix, int row, int col, boolean[][] alreadyFlashed) {
    addToCell(currMatrix, row - 1, col - 1, 1, alreadyFlashed);
    addToCell(currMatrix, row - 1, col, 1, alreadyFlashed);
    addToCell(currMatrix, row - 1, col + 1, 1, alreadyFlashed);
    addToCell(currMatrix, row, col + 1, 1, alreadyFlashed);
    addToCell(currMatrix, row + 1, col + 1, 1, alreadyFlashed);
    addToCell(currMatrix, row + 1, col, 1, alreadyFlashed);
    addToCell(currMatrix, row + 1, col - 1, 1, alreadyFlashed);
    addToCell(currMatrix, row, col - 1, 1, alreadyFlashed);
  }

  private void addToCell(int[][] matrix, int row, int col, int value, boolean[][] alreadyFlashed) {
    if (row >= 0
        && row < matrix.length
        && col >= 0
        && col < matrix[0].length
        && !alreadyFlashed[row][col]) {
      matrix[row][col] += value;
    }
  }

  private int sumFlashesInOneStep(int[][] matrix) {
    int flashes = 0;

    for (int row = 0; row < matrixHeight; row++) {
      for (int col = 0; col < matrixWidth; col++) {
        if (matrix[row][col] == 0) {
          ++flashes;
        }
      }
    }
    return flashes;
  }

  private boolean isAllFlash(int[][] matrix) {
    for (int row = 0; row < matrixHeight; row++) {
      for (int col = 0; col < matrixWidth; col++) {
        if (matrix[row][col] != 0) {
          return false;
        }
      }
    }

    return true;
  }
}
