import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 */
public class Puzzle {

    private Stack<Node> nodes = new Stack<Node>();
    private int maxStep = 1000;
    private int[][] map;

    public Puzzle(int[][] map) {
        this.map = map;
    }

    public int rows() {
        return map.length;
    }

    public int columns() {
        return map[0].length;
    }

    public boolean isObstacle(int x, int y) {
        return map[x][y] == 1;
    }

    public void findWay() {
        Node n = new Node(0, 0, this);
        tryNode(n);
    }

    public boolean containsNode(Node n) {
        return nodes.contains(n);
    }

    private void tryNode(Node n) {
        nodes.push(n);
        if (n.x == 10 && n.y == 10) {
            printPath();
        }
        for (Node t : n.nextNodes()) {
            tryNode(t);
        }
        nodes.pop();
    }

    public static void main(String[] args) {

        int[][] map = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                                   {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                                   {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
                                   {1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                                   {1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0},
                                   {0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0},
                                   {0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1},
                                   {1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0},
                                   {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                                   {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                   {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},};
        Puzzle puzzle = new Puzzle(map);
        puzzle.findWay();
    }

    private void printPath() {
        Node[] ls = nodes.toArray(new Node[nodes.size()]);
        if (ls.length < maxStep) {
            maxStep = ls.length;
            for (Node l : ls) {
                System.out.print("(" + l.x + "," + l.y + ")->");
            }
            System.out.println();
        }
    }

}

class Node {

    public int x;
    public int y;
    private Puzzle puzzle;

    public Node(int x, int y, Puzzle puzzle) {
        this.x = x;
        this.y = y;
        this.puzzle = puzzle;
    }

    @Override
    public int hashCode() {
        return x * 11 + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Node loc = (Node) obj;
        return loc.x == x && loc.y == y;
    }
    
    public List<Node> nextNodes() {
        List<Node> result = new ArrayList<Node>();
        addUp(result);
        addDown(result);
        addLeft(result);
        addRight(result);
        return result;
    }

    private void addDown(List<Node> result) {
        if (x < puzzle.rows()-1 && !puzzle.isObstacle(x+1, y)) {
            createAndAdd(x+1, y, result);
        }
    }

    private void addLeft(List<Node> result) {
        if (y > 0 && !puzzle.isObstacle(x, y-1)) {
            createAndAdd(x, y-1, result);
        }
    }

    private void addRight(List<Node> result) {
        if (y < puzzle.columns()-1 && !puzzle.isObstacle(x, y+1)) {
            createAndAdd(x, y+1, result);
        }
    }

    private void addUp(List<Node> result) {
        if (x > 0 && !puzzle.isObstacle(x-1, y)) {
            createAndAdd(x-1, y, result);
        }
    }

    private void createAndAdd(int x, int y, List<Node> result) {
        Node n = new Node(x, y, puzzle);
        if (!puzzle.containsNode(n)) {
            result.add(n);
        }
    }

}
