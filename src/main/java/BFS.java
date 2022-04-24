import org.apache.commons.lang.time.StopWatch;

import java.util.*;

public class BFS {

    public BFS() {
    }

    public Board bfs(Board startBoard, Board goal, String order) {
        Queue<Board> queue = new LinkedList<>();
        Set<Board> closed = new HashSet<>();
        if (startBoard.equals(goal)) {
            return startBoard;
        }
        queue.add(startBoard);
        Board tmp = null;
        while (!queue.isEmpty()) {
            tmp = queue.remove();
            closed.add(tmp);
            tmp.findNeighbours(order);
            for (Board n : tmp.neighbours
                 ) {
                //depth = n.depth;
                if (n.equals(goal)) {
                    n.processedStatesNumber = closed.size();
                    n.visitedStatesNumber = closed.size() + queue.size();
                    return n;
                }
                if (!queue.contains(n) && !closed.contains(n)) {
                    queue.add(n);
                }
            }
        }
        tmp.solutionSize = -1;
        return null;
    }
}

