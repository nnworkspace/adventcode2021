import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Day2Piloting {
  private static final String fileName = "day2-piloting";

  private List<String> data = null;

  public int calc() {
    if (data == null) {
      readData();
    }

    int posHorizontal = data.stream()
            .filter(line -> line.startsWith("forward"))
            .map(line -> Integer.parseInt(line.split(" ")[1].trim()))
            .reduce(0, Integer::sum);

    int posVerticalDown = data.stream()
            .filter(line -> line.startsWith("down"))
            .map(line -> Integer.parseInt(line.split(" ")[1].trim()))
            .reduce(0, Integer::sum);

    int posVerticalUp = data.stream()
            .filter(line -> line.startsWith("up"))
            .map(line -> -Integer.parseInt(line.split(" ")[1].trim()))
            .reduce(0, Integer::sum);

    System.out.println("final depth 1: " + (posVerticalDown + posVerticalUp));

    return posHorizontal * (posVerticalDown + posVerticalUp);
  }

  public int calc2() {
    if (data == null) {
      readData();
    }

    int aim = 0;
    int depth = 0;
    int posV = 0;
    for (String datum : data) {
      String[] subStrings = datum.split(" ");
      String direction = subStrings[0].trim();
      int amount = Integer.parseInt(subStrings[1].trim());

      switch (direction) {
        case "forward" -> {
          posV += amount;
          depth += amount * aim;
        }
        case "down" -> aim += amount;
        case "up" -> aim -= amount;
      }
    }

    System.out.println("final depth 2: " + depth);

    return posV * depth;
  }

  private void readData() {
    try {
      data = Files.lines(Paths.get(Objects.requireNonNull(this.getClass().getResource(fileName)).toURI())).toList();
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
  }
}
