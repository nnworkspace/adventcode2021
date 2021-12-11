package nnworkspace.adventcode2021;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 *  This solution works, but it's better to be solved with a Stack.....
 *
 *  https://adventofcode.com/2021/day/10
 */
public class Day10Syntax {
  private static final Logger logger = Logger.getLogger(Day10Syntax.class.getName());

  private ImmutableList<String> lines = null;

  private List<Character> opens;
  private List<Character> closes;

  public Day10Syntax(String fileName) {
    this.lines = PuzzlesUtil.getAllLines(fileName);

    String osStr = "([{<";
    String csStr = ")]}>";

    opens = osStr.chars().mapToObj(c -> (char) c).toList();
    closes = csStr.chars().mapToObj(c -> (char) c).toList();
  }

  public int calc() {

    // index 0 is for ), 1 is for ], 2 is for }, 3 is for >
    int[] scoreMap = {3, 57, 1197, 25137};

    int errorSum = 0;
    for (String line : lines) {
      // find first closing bracket
      StringBuilder sb = new StringBuilder(line);
      for (int i = 0; i < sb.length(); i++) {
        char right = sb.charAt(i);
        int idxInCloses = closes.indexOf(right);

        if (idxInCloses >= 0) {

          char left = sb.charAt(i - 1);
          int idxInOpens = opens.indexOf(left);

          if (idxInOpens != idxInCloses) {
            // corrupt
            errorSum += scoreMap[idxInCloses];
            break;
          } else {
            // remove itself and its matching opening from the list.
            // and reset i to i - 2
            sb.deleteCharAt(i);
            sb.deleteCharAt(i - 1);
            i = i - 2;
          }
        }
      }
    }
    return errorSum;
  }

  public long calc2() {

    List<Long> scores = new ArrayList<>();

    for (String line : lines) {
      // find first closing bracket
      StringBuilder sb = new StringBuilder(line);
      for (int i = 0; i < sb.length(); ++i) {
        char right = sb.charAt(i);
        int idxInCloses = closes.indexOf(right);

        if (idxInCloses < 0 && i == sb.length() - 1) {
          // all chars in the buffer are opening chars, calc the score
          scores.add(calcScore(sb));
        }

        if (idxInCloses >= 0) {

          char left = sb.charAt(i - 1);
          int idxInOpens = opens.indexOf(left);

          if (idxInOpens != idxInCloses) {
            // corrupt, skip this line, do nothing
            break;
          } else {
            // remove itself and its matching opening from the list.
            // and reset i to i - 2
            sb.deleteCharAt(i);
            sb.deleteCharAt(i - 1);
            i = i - 2;

            if (i == sb.length() - 1) {
              // the deleted pair was at the end of the string, calc the score
              scores.add(calcScore(sb));
            }
          }
        }
      }
    }

    Collections.sort(scores);
    int idxMiddle = scores.size() / 2;

    return scores.get(idxMiddle);
  }

  public long calcScore(StringBuilder sb) {
    // index 0 is for ), 1 is for ], 2 is for }, 3 is for >
    int[] scoreMap = {1, 2, 3, 4};

    long score = 0;
    for (int j = sb.length() - 1; j >= 0; j--) {
      char openC = sb.charAt(j);
      int charIdx = opens.indexOf(openC);
      score = score * 5 + scoreMap[charIdx];
    }

    if (score < 0) {
      logger.info(sb.toString());
    }

    return score;
  }
}
