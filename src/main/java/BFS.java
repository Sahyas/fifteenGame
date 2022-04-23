import java.util.*;

public class BFS {

    public int solutionLength;

    public BFS() {
    }

    public boolean search(Board startBoard, Board goal) {
        Queue<Board> queue = new LinkedList<>();
        Set<Board> closed = new HashSet<>();

        if (startBoard.equals(goal)) {
            return true;
        }
        queue.add(startBoard);
        while (!queue.isEmpty()) {
            Board tmp = queue.remove();
            closed.add(tmp);
            tmp.printBoard();
            System.out.print("\n");
            tmp.findNeighbours();
            for (int i = 0; i < tmp.neighbours.size(); i++) {
                if (tmp.neighbours.get(i).equals(goal)) {
                    tmp.solutionPath += "R";
                    tmp.printBoard();
                    System.out.print(tmp.solutionPath + "\n");
                    return true;
                }
                if (!queue.contains(tmp.neighbours.get(i)) && !closed.contains(tmp.neighbours.get(i))) {
                    queue.add(tmp.neighbours.get(i));
                }
            }
        }
        return false;
    }
}

