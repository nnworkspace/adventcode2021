package nnworkspace.adventcode2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** https://adventofcode.com/2021/day/8 */
public class Day8Segments {

  private static final Logger logger = Logger.getLogger(Day8Segments.class.getName());

  // Each number figure maps to segments:
  // 0 -> 6
  // 1 -> 2
  // 2 -> 5
  // 3 -> 5
  // 4 -> 4
  // 5 -> 5
  // 6 -> 6
  // 7 -> 3
  // 8 -> 7
  // 9 -> 6
  //
  // segment frequencies:
  // 0 -> 0
  // 1 -> 0
  // 2 -> 1 : 1
  // 3 -> 1 : 7
  // 4 -> 1 : 4
  // 5 -> 3 : 2, 3, 5
  // 6 -> 3 : 0, 6, 9
  // 7 -> 1 : 8

  private static final int[] uniqueFrequenciesSegsArray = {2, 3, 4, 7};
  private final Set<Integer> uniqueFrequenciesSegs;
  private List<List<String>> inputsList = new ArrayList<>();
  private List<List<String>> outputsList = new ArrayList<>();

  // private Set<Character> segs1 = new HashSet<>(Arrays.asList('C', 'F'));

  public Day8Segments() {
    uniqueFrequenciesSegs = new HashSet<>();
    Arrays.stream(uniqueFrequenciesSegsArray).forEach(uniqueFrequenciesSegs::add);
  }

  public List<List<String>> getInputsList() {
    return inputsList;
  }

  public List<List<String>> getOutputsList() {
    return outputsList;
  }

  public void readData(String fileName) {
    Stream<String> stream = null;

    try {
      stream =
              Files.lines(
                      Paths.get(Objects.requireNonNull(this.getClass().getResource(fileName)).toURI()));
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    assert stream != null;

    stream.forEach(
            line -> {
              String[] ioPair = line.split("\\|");
              this.inputsList.add(Arrays.stream(ioPair[0].trim().split("\\s+")).toList());
              this.outputsList.add(Arrays.stream(ioPair[1].trim().split("\\s+")).toList());
            });
  }

  public int calc() {
    return outputsList.stream()
        .map(
            outputs ->
                (int)
                    outputs.stream()
                        .filter(output -> uniqueFrequenciesSegs.contains(output.length()))
                        .count())
        .reduce(0, Integer::sum);
  }

  public int calc2() {
    // get an entry, find the input / output pair
    int outsSum = 0;


    for (int i = 0; i < inputsList.size(); i++) {
      List<String> ins = inputsList.get(i);
      List<String> outs = outputsList.get(i);

      List<Set<Character>> segMap = new ArrayList<>();
      decode(segMap, ins);

      int entrySum = 0;
      for (int j = 3; j >=0; --j) {
        Set<Character> ocSet = outs.get(j).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
        entrySum += segMap.indexOf(ocSet) * Math.pow(10, 3 - j);
      }

      outsSum += entrySum;
    }

    return outsSum;
  }

  private void decode(List<Set<Character>> segMap, List<String> inputs) {
    List<String> ins = new ArrayList<>(inputs);
    ins.sort(
            (o1, o2) -> {
              if (o1.length() < o2.length()) {
                return -1;
              } else if (o1.length() > o2.length()) {
                return 1;
              }
              return 0;
            });

    // the obvrious segments;
    Set<Character> segs1 = ins.get(0).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
    Set<Character> segs7 = ins.get(1).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
    Set<Character> segs4 = ins.get(2).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
    Set<Character> segs8 = ins.get(9).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());

    // segA: difference of segs1 and segs 7 is segA. (the first bar)
    Character segA = segs7.stream().filter(e -> !segs1.contains(e)).findFirst().get();

    // create a union of 4 and 7 to find the segG through 9.
    Character segG = findSegG(ins, segs7, segs4);

    // create a union of 7 and segG to find segD through 3
    Character segD = findSegD(ins, segs7, segG);

    // segB: segs4 remove segs1 and remove segD
    Character segB = findSegB(segs1, segs4, segD);

    // segE: create a union to find segE through 8
    Character segE = findSegE(segs7, segs8, segG, segD, segB);

    // segF: difference between segs6 and union of seg A, B, D, E, G
    Character segF = findSegF(ins, segA, segG, segD, segB, segE);

    // segC: difference between segs1 and segF
    segs1.remove(segF);
    Character segC = segs1.iterator().next();


    Character[] list0 = {segA, segB, segC, segE, segF, segG};
    Set<Character> set0 = new HashSet<>(Arrays.asList(list0));
    segMap.add(set0);

    Character[] list1 = {segC, segF};
    Set<Character> set1 = new HashSet<>(Arrays.asList(list1));
    segMap.add(set1);

    Character[] list2 = {segA, segC, segD, segE, segG};
    Set<Character> set2 = new HashSet<>(Arrays.asList(list2));
    segMap.add(set2);

    Character[] list3 = {segA, segC, segD, segF, segG};
    Set<Character> set3 = new HashSet<>(Arrays.asList(list3));
    segMap.add(set3);

    Character[] list4 = {segB, segC, segD, segF};
    Set<Character> set4 = new HashSet<>(Arrays.asList(list4));
    segMap.add(set4);

    Character[] list5 = {segA, segB, segD, segF, segG};
    Set<Character> set5 = new HashSet<>(Arrays.asList(list5));
    segMap.add(set5);

    Character[] list6 = {segA, segB, segD, segE, segF, segG};
    Set<Character> set6 = new HashSet<>(Arrays.asList(list6));
    segMap.add(set6);

    Character[] list7 = {segA, segC, segF};
    Set<Character> set7 = new HashSet<>(Arrays.asList(list7));
    segMap.add(set7);

    Character[] list8 = {segA, segB, segC, segD, segE, segF, segG};
    Set<Character> set8 = new HashSet<>(Arrays.asList(list8));
    segMap.add(set8);

    Character[] list9 = {segA, segB, segD, segC, segF, segG};
    Set<Character> set9 = new HashSet<>(Arrays.asList(list9));
    segMap.add(set9);
  }

  private Character findSegF(
      List<String> ins,
      Character segA,
      Character segG,
      Character segD,
      Character segB,
      Character segE) {

    Set<Character> unionABDEG = new HashSet<>();
    unionABDEG.add(segA);
    unionABDEG.add(segB);
    unionABDEG.add(segD);
    unionABDEG.add(segE);
    unionABDEG.add(segG);
    Character segF = null;
    for (int i = 6; i <= 8; i++) {
      Set<Character> segs6Tmp =
          ins.get(i).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
      segs6Tmp.removeAll(unionABDEG);
      if (segs6Tmp.size() == 1) {
        segF = segs6Tmp.iterator().next();
        break;
      }
    }
    return segF;
  }

  private Character findSegE(
      Set<Character> segs7, Set<Character> segs8, Character segG, Character segD, Character segB) {
    Set<Character> union7BDG = new HashSet<>(segs7);
    union7BDG.add(segB);
    union7BDG.add(segD);
    union7BDG.add(segG);
    Set<Character> segs8Copy = new HashSet<>(segs8);
    segs8Copy.removeAll(union7BDG);
    return segs8Copy.iterator().next();
  }

  private Character findSegB(Set<Character> segs1, Set<Character> segs4, Character segD) {
    Set<Character> segs4Copy = new HashSet<>(segs4);
    segs4Copy.removeAll(segs1);
    segs4Copy.remove(segD);
    return segs4Copy.iterator().next();
  }

  private Character findSegD(List<String> ins, Set<Character> segs7, Character segG) {
    Set<Character> union7G = new HashSet<>(segs7);
    union7G.add(segG);

    Character segD = null;
    for (int i = 3; i <= 5; i++) {
      Set<Character> segs3Tmp =
          ins.get(i).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
      segs3Tmp.removeAll(union7G);
      if (segs3Tmp.size() == 1) {
        segD = segs3Tmp.iterator().next();
        break;
      }
    }
    return segD;
  }

  private Character findSegG(List<String> ins, Set<Character> segs7, Set<Character> segs4) {
    Set<Character> union47 = new HashSet<>(segs4);
    union47.addAll(segs7);

    Character segG = null;
    for (int i = 6; i <= 8; i++) {
      Set<Character> segs7Tmp =
          ins.get(i).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
      segs7Tmp.removeAll(union47);
      if (segs7Tmp.size() == 1) {
        segG = segs7Tmp.iterator().next();
        break;
      }
    }
    return segG;
  }
}
