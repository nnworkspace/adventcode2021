package nnworkspace.adventcode2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/** https://adventofcode.com/2021/day/5 */
public class Day5Vents {
  private static final Logger logger = Logger.getLogger(Day5Vents.class.getName());

  private int maxX, maxY;

  private int[][] oceanFloorMap;

  private List<int[]> ventPosConfigs;

  public Day5Vents(int maxX, int maxY) {
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public void readData(String configFile) {
    try {
      this.ventPosConfigs =
          Files.lines(
                  Paths.get(
                      Objects.requireNonNull(this.getClass().getResource(configFile)).toURI()))
              .map(this::parseCoordinates)
              .toList();
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  public int calc() {
    initOceanFloorMap();
    List<int[]> lines = ventPosConfigs.stream().filter(this::isVentFlat).toList();
    int result = countDangerousPointsFlat(lines);

    String msg = String.format("dangerous points = %d.", result);
    logger.info(msg);

    return result;
  }

  public int calc2() {
    initOceanFloorMap();

    List<int[]> lines = ventPosConfigs.stream().filter(this::isVentFlat).toList();

    int result = countDangerousPointsFlat(lines);

    lines = ventPosConfigs.stream().filter(this::isVentDiagonal).toList();

    result += countDangerousPointsDiagonal(lines);

    String msg = String.format("dangerous points = %d.", result);
    logger.info(msg);

    return result;
  }

  public int[][] getOceanFloorMap() {
    return oceanFloorMap;
  }

  public List<int[]> getVentPosConfigs() {
    return ventPosConfigs;
  }

  private void initOceanFloorMap() {
    this.oceanFloorMap = new int[maxX][maxY];
    for (int i = 0; i < maxX; i++) {
      for (int j = 0; j < maxY; j++) {
        oceanFloorMap[i][j] = 0;
      }
    }
  }

  private int[] parseCoordinates(String text) {
    // linePos[0] is x1,
    // linePos[1] is y1,
    // linePos[2] is x2,
    // linePos[3] is y2.
    int[] linePos = new int[4];
    String[] pointsStr = text.trim().split("->");
    String pointStr = pointsStr[0];
    String[] posStrs = pointStr.split(",");
    linePos[0] = Integer.parseInt(posStrs[0].trim());
    linePos[1] = Integer.parseInt(posStrs[1].trim());

    pointStr = pointsStr[1];
    posStrs = pointStr.split(",");
    linePos[2] = Integer.parseInt(posStrs[0].trim());
    linePos[3] = Integer.parseInt(posStrs[1].trim());
    return linePos;
  }

  private int countDangerousPointsFlat(List<int[]> lines) {
    int dangerousPoints = 0;

    for (int[] pos : lines) {
      int x1 = pos[0];
      int y1 = pos[1];
      int x2 = pos[2];
      int y2 = pos[3];
      for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
          oceanFloorMap[x][y]++;

          if (oceanFloorMap[x][y] == 2) {
            dangerousPoints++;
          }
        }
      }
    }
    // String msg = String.format("maxOverlaps = %d, occurrance = %d.", maxOverlaps,
    // dangerousPoints);

    return dangerousPoints;
  }

  private int countDangerousPointsDiagonal(List<int[]> lines) {
    int dangerousPoints = 0;

    for (int[] pos : lines) {
      int x1 = pos[0];
      int y1 = pos[1];
      int x2 = pos[2];
      int y2 = pos[3];

      // An entry like 1,1 -> 3,3 covers points 1,1, 2,2, and 3,3.
      if ((x2 > x1 && y2 > y1) || (x1 > x2 && y1 > y2)) {
        for (int x = Math.min(x1, x2), y = Math.min(y1, y2); x <= Math.max(x1, x2); x++, y++) {
          oceanFloorMap[x][y]++;

          if (oceanFloorMap[x][y] == 2) {
            dangerousPoints++;
          }
        }
      } else {
        // An entry like 11,5 -> 7,9 covers points (11,5), (10,6), (9,7), (8,8), and (7,9).
        for (int x = Math.min(x1, x2), y = Math.max(y1, y2); x <= Math.max(x1, x2); x++, y--) {
          oceanFloorMap[x][y]++;

          if (oceanFloorMap[x][y] == 2) {
            dangerousPoints++;
          }
        }
      }
    }

    return dangerousPoints;
  }

  private boolean isVent(int[] pos) {
    return this.isVentFlat(pos) || isVentDiagonal(pos);
  }

  private boolean isVentFlat(int[] pos) {
    boolean result = false;

    int x1 = pos[0];
    int y1 = pos[1];
    int x2 = pos[2];
    int y2 = pos[3];

    // vertical line
    if (x1 == x2) {
      return true;
    }

    // horizontal line
    if (y1 == y2) {
      return true;
    }

    return result;
  }

  private boolean isVentDiagonal(int[] pos) {
    boolean result = false;

    int x1 = pos[0];
    int y1 = pos[1];
    int x2 = pos[2];
    int y2 = pos[3];

    // diagonal line
    if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
      return true;
    }

    return result;
  }
}
