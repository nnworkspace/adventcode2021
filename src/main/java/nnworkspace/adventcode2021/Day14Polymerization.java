package nnworkspace.adventcode2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Day14Polymerization {
  private static final Logger logger = Logger.getLogger(Day13Origami.class.getName());

  private Map<String, Character> insertionRules = new HashMap<>();
  private StringBuilder polymer;

  private Map<Character, Long> charCounters = new HashMap<>();

  public Day14Polymerization(String configFile) {
    parseData(configFile);
  }

  public long calc(int steps) {

    for (int s = 0; s < steps; s++) {
      System.out.println("Start step " + (s + 1));

      //resetCounterMap();

      for (int i = polymer.length() - 1; i >= 1; i--) {
        // update counter
        //updateCounterForCharAt(i);

        String key = polymer.substring(i - 1, i + 1);
        if (insertionRules.containsKey(key)) {
          Character ins = insertionRules.get(key);
          polymer.insert(i, ins);

          // update counter
          //charCounters.put(ins, charCounters.get(ins) + 1);
        }
      }

      //updateCounterForCharAt(0);
    }

    // find out the most common char and least common char.
    long result = calcMLDelta();

    return result;
  }

  private void resetCounterMap() {
    for (Map.Entry<Character, Long> entry : charCounters.entrySet()) {
      entry.setValue(0L);
    }
  }

  private long calcMLDelta() {

    for (int i = 0; i < polymer.length(); i++) {
      char c = polymer.charAt(i);
      charCounters.put(c, charCounters.get(c) + 1);
    }

    Character mostCommon = null;
    long mCounter = 0;
    Character leastCommon = null;
    long lCounter = Integer.MAX_VALUE;
    for (Map.Entry<Character, Long> charCounter : charCounters.entrySet()){
      if (charCounter.getValue() > mCounter) {
        mCounter = charCounter.getValue();
        mostCommon = charCounter.getKey();
      }

      if (charCounter.getValue() < lCounter) {
        lCounter = charCounter.getValue();
        leastCommon = charCounter.getKey();
      }
    }

    long result = mCounter - lCounter;
    return result;
  }

  private void updateCounterForCharAt(int i) {
    char charI = polymer.charAt(i);
    charCounters.put(charI, charCounters.get(charI) + 1);
  }

  /**
   * impossible CPU time.....
   *
   * @param steps
   * @return
   */
  public long calc2(int steps) {
    return calc(steps);
  }

  private void parseData(String configFile) {
    try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {

      // parse first line
      parseTemplate(br);

      // skip the second line
      br.readLine();

      // parse the insertion instructions
      String line;
      while ((line = br.readLine()) != null) {
        String[] insertion = line.split("->");
        String key = insertion[0].trim();
        String value = insertion[1].trim();
        insertionRules.put(key, value.charAt(0));

        // init the char counter map
        charCounters.put(key.charAt(0), 0L);
        charCounters.put(key.charAt(1), 0L);
        charCounters.put(value.charAt(0), 0L);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void parseTemplate(BufferedReader br) throws IOException {
    String line = br.readLine().trim();
    polymer = new StringBuilder(line);

    // init the char counter map
    for (int i = 0; i < polymer.length(); i++) {
      charCounters.put(line.charAt(i), 0L);
    }
  }
}
