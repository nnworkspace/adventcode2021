package nnworkspace.adventcode2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** https://adventofcode.com/2021/day/4 */
public class Day4Bingo {
  private static final Logger logger = Logger.getLogger(Day4Bingo.class.getName());

  private int boardSize = 0;

  List<Integer> randomInts = null;
  List<int[][]> boards = new ArrayList<>();
  // List<List<Pair>> marks = new ArrayList<>();

  public Day4Bingo(int boardSize) {
    this.boardSize = boardSize;
  }

  // public easier for testing....
  public void readData(String configFile) {
    try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
      readRandomNumbers(br);
      readBoards(br);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int[] calc() {
    int bingoRandom = -1;
    int bingoScore = -1;
    for (int random : randomInts) {
      for (int[][] board : boards) {
        // first, mark the matching number as -1
        boolean matchMarked = markAMatch(random, board);

        if (matchMarked) {
          // then scan the whole board, if there is a row or col already has all -1
          bingoScore = checkAndCalcBingo(random, board);
          if (bingoScore > 0) {
            bingoRandom = random;
            String msg = String.format("Random = %d, bingoScore=%d", random, bingoScore);
            logger.log(Level.INFO, msg);
            return new int[]{bingoRandom, bingoScore};
          }
        }
      }
    }

    return new int[]{bingoRandom, bingoScore};
  }

  public int calc2() {
    return 0;
  }

  private int checkAndCalcBingo(int random, int[][] board) {
    // is a whole row already marked? if yes, calc bingo score
    int bingoScore = checkBingoInRows(random, board);
    if (bingoScore <= 0 ) {
      bingoScore = checkBingoInCols(random, board);
    }
    return bingoScore;
  }

  private int checkBingoInRows(int random, int[][] board) {
    for (int row = 0; row < boardSize; row++) {
      int[] aRow = board[row];
      int rowSum = Arrays.stream(aRow).reduce(0, Integer::sum);
      if (rowSum == -1 * boardSize) {
        // bingo
        return calcBingoScore(board, random);
      }
    }
    return -1;
  }

  private int checkBingoInCols(int random, int[][] board) {
    for (int col = 0; col < boardSize; col++){
      int colSum = 0;
      for (int row = 0; row < boardSize; row++){
        colSum += board[row][col];
      }
      if ( colSum == -1 * boardSize) {
        // bingo
        return calcBingoScore(board, random);
      }
    }
    return -1 ;
  }

  private boolean markAMatch(int random, int[][] board) {
    boolean matchMarked = false;
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (board[row][col] == random) {
          board[row][col] = -1;
          matchMarked = true;
        }
      }
    }
    return matchMarked;
  }

  public int calcBingoScore(int[][] board, int random) {
    int rowSum = 0;
    for (int row = 0; row < boardSize; row++) {
      rowSum += Arrays
              .stream(board[row])
              .filter(number -> number > -1)
              .reduce(0, Integer::sum);
    }
    return rowSum * random;
  }

  public List<Integer> getRandomInts() {
    return randomInts;
  }

  public List<int[][]> getBoards() {
    return boards;
  }

  private void readRandomNumbers(BufferedReader br) throws IOException {
    String firstLine = br.readLine();
    this.randomInts =
        Arrays.stream(firstLine.split(",")).map(intStr -> Integer.parseInt(intStr.trim())).toList();
  }

  private void readBoards(BufferedReader br) throws IOException {
    String line;
    int idx = 0;
    int lineGroupSize = 1 + boardSize; // one blank line above a board

    int[][] board = null;
    while ((line = br.readLine()) != null) {
      if (idx % lineGroupSize == 0) {
        // this is a blank line, in this case do two things:
        // 1. add the existing board to the board list, and
        // 2. create a new empty board, read next line;
        if (idx != 0) {
          boards.add(board);
        }
        board = new int[boardSize][boardSize];
      } else {
        int rowIdxInBoard = idx % lineGroupSize - 1;
        String[] colStrs = line.trim().split("\\s+");

        // logger.log(Level.INFO, "idx = " + idx + ", colStrs = " + Arrays.toString(colStrs));

        for (int i = 0; i < boardSize; i++) {
          board[rowIdxInBoard][i] = Integer.parseInt(colStrs[i].trim());
        }
      }
      idx++;
    }

    // add the last board
    boards.add(board);
  }
}
