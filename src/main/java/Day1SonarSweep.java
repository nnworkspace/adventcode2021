import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Day1SonarSweep {

    private List<Integer> data = null;

    private void readData() {
        Stream<String> stream = null;
        try {
            stream = Files.lines(Paths.get(Objects.requireNonNull(this.getClass().getResource("day1-sonar-sweep")).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        assert stream != null;
        this.data = stream.map(line -> Integer.parseInt(line.trim())).toList();
    }

    public int calc() {
        if (this.data == null) {
            this.readData();
        }

        int counter = 0;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) > data.get(i - 1)) {
                counter++;
            }
        }

        return counter;
    }

    public int calc2() {
        if (this.data == null) {
            this.readData();
        }

        int counter = 0;
        for (int i = 3; i < data.size(); i++) {
            if (data.get(i) > data.get(i - 3)) {
                counter++;
            }
        }

        return counter;
    }
}
