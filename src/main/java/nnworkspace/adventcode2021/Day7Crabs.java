package nnworkspace.adventcode2021;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

/** https://adventofcode.com/2021/day/7 */
public class Day7Crabs {
  private static final Logger logger = Logger.getLogger(Day7Crabs.class.getName());

  private ImmutableList<Integer> posHList;
  private int maxX;

  public Day7Crabs(String posFile) {
    this.posHList = PuzzlesUtil.parseIntsInFirstLine(posFile);
    this.maxX = Collections.max(posHList);
  }

  public int calc() {
    // make a histogram
    int[] hPosHist = initHistogram();

    int minFuel = Integer.MAX_VALUE;

    for (int i = 0; i < hPosHist.length; i++) {
      int fuel = 0;
      for (int j = 0; j < hPosHist.length; j++) {
        fuel += hPosHist[j] * calcFuelFactor(i, j);
      }

      if (fuel < minFuel) {
        minFuel = fuel;
      }
    }

    return minFuel;
  }

  public int calc2() {
    // make a histogram
    int[] hPosHist = initHistogram();

    int minFuel = Integer.MAX_VALUE;

    for (int i = 0; i < hPosHist.length; i++) {
      int fuel = 0;
      for (int j = 0; j < hPosHist.length; j++) {
        fuel += hPosHist[j] * calcFuelFactor2(i, j);
      }
      if (fuel < minFuel) {
        minFuel = fuel;
      }
    }

    return minFuel;
  }

  private int[] initHistogram() {
    int[] hPosHist = new int[maxX + 1];
    Arrays.stream(hPosHist).forEach(frequency -> frequency = 0);

    posHList.forEach(pos -> ++hPosHist[pos]);
    return hPosHist;
  }

  private int calcFuelFactor(int i, int j) {
    return Math.abs(i - j);
  }

  public int calcFuelFactor2(int i, int j) {
    int dist = Math.abs(i - j);
    return (1 + dist) * dist / 2;
  }

  //  private List<Integer> findModes(int[] hPosHist) {
  //    int maxFrequency = 0;
  //    List<Integer> modes = new ArrayList<>();
  //    for (int i = 0; i < hPosHist.length; i++) {
  //      if (hPosHist[i] > maxFrequency) {
  //        maxFrequency = hPosHist[i];
  //        modes.clear();
  //        modes.add(i);
  //        continue;
  //      }
  //
  //      if (hPosHist[i] == maxFrequency) {
  //        modes.add(i);
  //      }
  //    }
  //
  //    return modes;
  //  }

}
