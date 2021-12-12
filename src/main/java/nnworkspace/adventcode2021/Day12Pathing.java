package nnworkspace.adventcode2021;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Backtracking. Important is to construct the constraints.
 * Here, one of the constraints is how many times a node can be visited.
 *
 * Actually the method <code>move</code> and <code>moveQ2</code> here
 * are the same thing, it's just the question 1 uses only one constraint map.
 * I could have merged these two methods, but simply left them as they are,
 * to see how the solution generalizes from Q1 to Q2.
 *
 * <p>https://adventofcode.com/2021/day/12
 */
public class Day12Pathing {
  private static final Logger logger = Logger.getLogger(Day12Pathing.class.getName());

  /**
   * In the list, each String[] has only two elements
   * string[0] is the start node and string[1] is the end node.
   * These two nodes represents an edge
   *
   * While a -> b is an edge, b -> a is also an edge.
   */
  List<String[]> edges;

  List<String> smallCaves;

  int paths = 0;

  /**
   * Key is the node string, value is the visits count
   */
  Map<String, Integer> nodeVisitsMap;

  String fileName = null;

  public Day12Pathing(String fileName) {
    this.fileName = fileName;
  }

  public int calc() {
    init(fileName);

    move("start", "");

    return paths;
  }

  public int calc2() {
    init(fileName);

    Set<String> paths = new HashSet<>();

    List<Map<String, Integer>> constraints = this.getConstraints();

    for (Map<String, Integer> consMap : constraints) {

      // reset the nodeVisitsMap
      this.nodeVisitsMap.replaceAll((k, v) -> v = 0);

      moveQ2("start", "", consMap, paths);
    }

    return paths.size();
  }


  /**
   * each small cave has one chance to be visited twice, generate all possible constraints.
   * @return
   */
  private List<Map<String, Integer>> getConstraints() {
    List<Map<String, Integer>> constraints = new ArrayList<>();
    for (int i = 0; i < smallCaves.size() ; i++) {
      Map<String, Integer> cons = new HashMap<>();
      cons.put("start", 1);
      cons.put("end", 1);

      for (int j = 0; j < smallCaves.size(); j++) {
        String cave = smallCaves.get(j);

        if (i == j) {
          cons.put(cave, 2);
        } else {
          cons.put(cave, 1);
        }
      }

      constraints.add(cons);
    }

    return constraints;
  }

  private void init(String fileName) {
    smallCaves = new ArrayList<>();
    edges = new ArrayList<>();
    nodeVisitsMap = new HashMap<>();
    paths = 0;

    Stream<String> linesStream = PuzzlesUtil.readAllLines(fileName);
    List<String[]> tmp = linesStream.map(line -> line.split("-")).toList();
    tmp.forEach(
        nodes -> {
          String a = nodes[0].trim();
          String b = nodes[1].trim();
          edges.add(new String[] {a, b});

          // add (b, a) because b-a is also an edge.
          // b -> a is also a valid move
          edges.add(new String[] {b, a});

          // fill the nodes list
          if (isMiddleSmallCaves(a) && !smallCaves.contains(a)) {
            smallCaves.add(a);
          }

          if (isMiddleSmallCaves(b) && !smallCaves.contains(b)) {
            smallCaves.add(b);
          }

          // init the node-visits map.
          nodeVisitsMap.put(a, 0);
          nodeVisitsMap.put(b, 0);
        });

    Collections.sort(smallCaves);
  }

  private boolean isMiddleSmallCaves(String cave) {
    return !isBigCave(cave) && !cave.equals("start") && !cave.equals("end");
  }

  /**
   * a recursive backtracking method
   * @param currNode
   * @param pathStr
   */
  private void move(String currNode, String pathStr) {

    //int p = paths;

    int visits = nodeVisitsMap.get(currNode);
    nodeVisitsMap.put(currNode, ++visits);

    pathStr += "->" + currNode;

    if (currNode.equals("end")) {
      // logger.info(pathStr);
      ++paths;

      nodeVisitsMap.put(currNode, --visits);
      // return to last call of move
      return;
    }

    for (String[] nodes: edges) {
      if (nodes[0].equals(currNode)) {
        String n2 = nodes[1];
        int v = nodeVisitsMap.get(n2);
        if (isBigCave(n2) || v == 0) {
          move(n2, pathStr);
        }
      }
    }

    visits = nodeVisitsMap.get(currNode);
    nodeVisitsMap.put(currNode, --visits);
  }

  /**
   * a recursive backtracking method with another constraint
   * @param currNode
   * @param pathStr
   */
  private void moveQ2(String currNode, String pathStr, Map<String, Integer> consMap, Set<String> paths) {

    //int p = paths;

    int visits = nodeVisitsMap.get(currNode);
    nodeVisitsMap.put(currNode, ++visits);

    pathStr += "->" + currNode;

    if (currNode.equals("end")) {
      // logger.info(pathStr);
      paths.add(pathStr);

      nodeVisitsMap.put(currNode, --visits);
      // return to last call of move
      return;
    }

    for (String[] nodes: edges) {
      if (nodes[0].equals(currNode)) {
        String n2 = nodes[1];

        if (canMoveTo(n2, consMap)) {
          moveQ2(n2, pathStr, consMap, paths);
        }
      }
    }

    visits = nodeVisitsMap.get(currNode);
    nodeVisitsMap.put(currNode, --visits);
  }

  private boolean canMoveTo(String node, Map<String, Integer> consMap){

    int v = nodeVisitsMap.get(node);

    if (isBigCave(node)) {
      return true;
    }

    return v < consMap.get(node);
  }

  /**
   * this only works if the symbol of cave is in English...
   *
   * @param caveSymbol
   * @return
   */
  private boolean isBigCave(String caveSymbol) {
    char c = caveSymbol.charAt(0);

    if ('A' <= c && c <= 'Z') {
      return true;
    }

    return false;
  }
}
