package nnworkspace.adventcode2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Day3Diagnostic {
    private static final String fileName = "/day3-binary-diagnostic";

    private List<String> data = null;

    public int calc() {
        if (data == null) {
            readData();
        }

        int lineWidth = data.get(0).trim().length();
        int totalLines = data.size();

        int gammaRate = 0;
        int epsilonRate = 0;

        // very inefficient I know, but well, the map reduce is simply to easy to write.....
        for (int i = 0; i < lineWidth; i++) {
            final int finalI = i;
            int colSumI = data.stream()
                    .map(line -> Integer.parseInt(String.valueOf(line.trim().charAt(finalI))))
                    .reduce(0, Integer::sum);

            int increment = (int) Math.pow(2, lineWidth - (i + 1));

            // what if the totalLines is an even number and colSimI is equal to totalLines/2?
            // it was not specified in the requirement spec, this is a bug in the spec......
            if (colSumI > totalLines / 2) {
               gammaRate += increment;
            } else {
               epsilonRate += increment;
            }
        }

        System.out.printf("gamma: %d, epsilon: %d", gammaRate, epsilonRate);
        System.out.println();

        return gammaRate * epsilonRate;
    }

    public int calc2() {
        if (data == null) {
            readData();
        }

        int lineWidth = data.get(0).trim().length();

        int o2GenRate = this.calcO2GenRate(lineWidth);
        int co2GenRate = this.calcCo2GenRate(lineWidth);

        System.out.println();
        System.out.printf("o2GenRate: %d, co2GenRate: %d", o2GenRate, co2GenRate);
        System.out.println();

        return o2GenRate * co2GenRate;
    }

    private int calcO2GenRate(int lineWidth) {
        List<String> o2List = data;
        String o2 = null;
        for (int i = 0; i < lineWidth; i++) {
            final int finalI = i;
            int colSumI = o2List.stream()
                    .map(line -> Integer.parseInt(String.valueOf(line.trim().charAt(finalI))))
                    .reduce(0, Integer::sum);
            double bar = o2List.size() / 2.0;

            // oxygen rate:
            char mostCommon = colSumI >= bar ? '1' : '0';

            o2List = o2List.stream().filter(line -> line.charAt(finalI) == mostCommon).toList();
            if (o2List.size() == 1) {
                o2 = o2List.get(0);
                System.out.printf("O2 string is: %s, iteration: %d; ", o2, i);
                break;
            }

           //System.out.println("i = " + i + ", o2List is: " + o2List);
        }

        assert o2 != null;
        return Integer.parseInt(o2, 2);
    }

    private int calcCo2GenRate(int lineWidth) {
        List<String> co2List = data;
        String co2 = null;
        for (int i = 0; i < lineWidth; i++) {
            final int finalI = i;
            int colSumI = co2List.stream()
                    .map(line -> Integer.parseInt(String.valueOf(line.trim().charAt(finalI))))
                    .reduce(0, Integer::sum);
            double bar = co2List.size() / 2.0;

            // co2 rate:
            char leastCommon = colSumI < bar ? '1' : '0';

            co2List = co2List.stream().filter(line -> line.charAt(finalI) == leastCommon).toList();
            if (co2List.size() == 1) {
                co2 = co2List.get(0);
                System.out.printf("Co2 string is: %s, iteration: %d.", co2, i);
                break;
            }

            //System.out.println("i = " + i + ", co2List is: " + co2List);
        }

        assert co2 != null;
        return Integer.parseInt(co2, 2);
    }

    private void readData() {
        try {
            data = Files.lines(Paths.get(Objects.requireNonNull(this.getClass().getResource(fileName)).toURI())).toList();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
