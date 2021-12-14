package nnworkspace.adventcode2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class Day13Origami {
  private static final Logger logger = Logger.getLogger(Day13Origami.class.getName());
  int maxX, maxY;
  /** each int[] in the List has only two elements. int[0] is x, int[1] is y */
  private List<Position> dotPositions = new ArrayList<>();
  /**
   * each int[] in the List has only two elements. int[0] is x, int[1] is y. if folded along y = 7,
   * the instruction is coded as int[0] = 0, int[1] = 7 if folded along x = 5, the instruction is
   * coded as int[0] = 5, int[1] = 0
   */
  private List<int[]> folds = new ArrayList<>();

  public Day13Origami(String filePath) {
    this.parseData(filePath);
  }

  public int calc(int doFolds) {

    for (int i = 0; i < doFolds; i++) {
      Set<Position> dots = new HashSet<>();

      int[] foldCode = folds.get(i);

      // if fold along x
      if (foldCode[1] == 0) {
        foldAlongX(dots, foldCode);
      }

      // if fold along y
      if (foldCode[0] == 0) {
        foldAlongY(dots, foldCode);
      }

      dotPositions = dots.stream().toList();
    }


    return dotPositions.size();
  }

  private void foldAlongY(Set<Position> dots, int[] foldCode) {
    int fPos = foldCode[1];

    for (Position dotPos :  dotPositions) {

      int x = dotPos.x;
      int y = dotPos.y;

      // unfolded part, add dot pos as it is.
      if ( y < fPos) {
        dots.add(dotPos);
      }

      if (y == fPos) {
        // do nothing
      }

      if (y > fPos) {
        int ny = fPos - ( y - fPos);
        dots.add(new Position(x, ny));
      }
    }
  }

  private void foldAlongX(Set<Position> dots, int[] foldCode) {
    int fPos = foldCode[0];

    for (Position dotPos :  dotPositions) {

      int x = dotPos.x;
      int y = dotPos.y;

      // unfolded part, add dot pos as it is.
      if (x < fPos) {
        dots.add(dotPos);
      }

      if (x == fPos) {
        // do nothing
      }

      if (x > fPos) {
        int nx = fPos - ( x - fPos);
        dots.add(new Position(nx, y));
      }
    }
  }

  public int calc2() {
    int dots = calc(this.folds.size());

    int mx = 0;
    int my = 0;
    for (Position p : dotPositions) {
      if (p.x > mx) {
        mx = p.x;
      }

      if (p.y > my) {
        my = p.y;
      }
    }

    for (int y = 0; y <= my; y++) {
      for (int x = 0; x <= mx; x++) {
        Position p = new Position(x, y);
        String s = dotPositions.contains(p) ? "#" : " ";
        System.out.print(s);
      }
      System.out.println();
    }

    return dots;
  }

  public void parseData(String filePath) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String line;
      int mx = 0;
      int my = 0;
      while ((line = br.readLine()) != null) {

        if (line.isBlank()) {
          continue;
        }

        if (!line.startsWith("fold")) {
          String[] posStrs = line.split(",");
          int[] pos = {Integer.parseInt(posStrs[0].trim()), Integer.parseInt(posStrs[1].trim())};

          if (pos[0] > mx) {
            mx = pos[0];
          }

          if (pos[1] > my) {
            my = pos[1];
          }

          dotPositions.add(new Position(pos[0], pos[1]));
        } else {
          int idx = line.indexOf("=");
          char key = line.charAt(idx - 1);
          int value = Integer.parseInt(line.substring(idx + 1).trim());

          switch(key) {
            case 'x' -> folds.add(new int[]{value, 0});
            case 'y' -> folds.add(new int[]{0, value});
          }
        }
      }

      this.maxX = mx;
      this.maxY = my;

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
