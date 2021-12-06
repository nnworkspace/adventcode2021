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

  private ImmutableList<Short> initialTimers;

  private short cycleLength, maturityDuration;

  public Day6Lanternfish(short cycleLength, short maturityDuration) {
    this.cycleLength = cycleLength;
    this.maturityDuration = maturityDuration;
  }

  /**
   * very inefficient algorithm, but very easy to come up with and easy to read
   *
   * @param days
   * @return
   */
  public long calc(int days) {
    List<Short> timers = new ArrayList<>(this.initialTimers);

    for (int day = 1; day <= days; day++) {
      for (int i = 0; i < timers.size(); i++) {
        int curr = timers.get(i);
        if (curr == 0) {
          timers.add(maturityDuration);
          timers.set(i, (short) (cycleLength - 1));
        } else {
          timers.set(i, (short) --curr);
        }

        // System.gc();
      }
      // System.gc();
      logDayPopulation(day, timers.size());
    }

    int population = timers.size();
    logDayPopulation(days, population);

    return population;
  }

  /**
   * for days greater than 200, the algorithm in calc() will not work due to heap size overflow. So
   * another alrorightm here.
   *
   * @param days
   * @return
   */
  public long calc2(int days) {

    // in this array, the
    // 0th value is counter for timer with value 0
    // 1st value is counter for timer with value 1
    // ...
    // 8th value is counter for timer with value 8
    long[] timerCounters = new long[9];
    initTimerCounters(timerCounters);

    for (int day = 1; day <= days; day++) {

      long[] prevCounters = timerCounters.clone();

      for (int i = 0; i < 9; ++i) {
        if (i == 8) {
          timerCounters[i] = prevCounters[0];
        } else if (i == 6) {
          timerCounters[6] = prevCounters[0] + prevCounters[7];
        } else {
          timerCounters[i] = prevCounters[i + 1];
        }
      }
    }

    long population = Arrays.stream(timerCounters).reduce(0, Long::sum);
    logDayPopulation(days, population);

    return population;
  }

  public void readData(String configFile) {
    try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
      String firstLine = br.readLine();
      this.initialTimers =
          ImmutableList.copyOf(
              Arrays.stream(firstLine.split(","))
                  .map(intStr -> Short.parseShort(intStr.trim()))
                  .toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Short> getInitialTimers() {
    return initialTimers;
  }

  private void initTimerCounters(long[] timerCounters) {
    for (int i = 0; i < 9; i++) {
      timerCounters[i] = 0;
    }
    this.initialTimers.forEach(timer -> ++timerCounters[timer]);
  }

  private void logDayPopulation(int days, long population) {
    String msg = String.format("After %d days, the lanternfish population is %d", days, population);
    logger.info(System.lineSeparator() + msg + System.lineSeparator());
  }
}
