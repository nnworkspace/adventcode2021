package nnworkspace.adventcode2021;

import com.google.common.collect.ImmutableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/** https://adventofcode.com/2021/day/6 */
public class Day6Lanternfish {
  private static final Logger logger = Logger.getLogger(Day6Lanternfish.class.getName());

  private ImmutableList<Integer> initialTimers;

  private int cycleLength, maturityDuration;

  public Day6Lanternfish(int cycleLength, int maturityDuration) {
    this.cycleLength = cycleLength;
    this.maturityDuration = maturityDuration;
  }

  public long calc(int days) {
    List<Integer> timers = new ArrayList<>(this.initialTimers);

    for (int day = 1; day <= days; day++) {
      for (int i = 0; i < timers.size(); i++) {
        int curr = timers.get(i);
        if (curr == 0) {
          timers.add(maturityDuration);
          timers.set(i, cycleLength - 1);
        } else {
          timers.set(i, --curr);
        }
      }
      // logger.info(System.lineSeparator() + timers + System.lineSeparator());
    }

    int population = timers.size();

    String msg = String.format("After %d days, the lanternfish population is %d", days, population);

    logger.info(System.lineSeparator() + msg + System.lineSeparator());

    return population;
  }

  public int calc2(int days) {
    return 0;
  }

  public void readData(String configFile) {
    try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
      String firstLine = br.readLine();
      this.initialTimers =
          ImmutableList.copyOf(
              Arrays.stream(firstLine.split(","))
                  .map(intStr -> Integer.parseInt(intStr.trim()))
                  .toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Integer> getInitialTimers() {
    return initialTimers;
  }
}
