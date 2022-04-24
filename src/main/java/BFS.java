import org.apache.commons.lang.time.StopWatch;

import java.util.*;

public class BFS {

    public BFS() {
    }

    public boolean bfs(Board startBoard, Board goal) {
        long start = System.currentTimeMillis();
        FileManager file = new FileManager();
        Queue<Board> queue = new LinkedList<>();
        Set<Board> closed = new HashSet<>();
        int visitedStatesNumber = 0;
        int processedStatesNumber;
        int depth;
        if (startBoard.equals(goal)) {
            return true;
        }
        queue.add(startBoard);
        Board tmp = null;
        while (!queue.isEmpty()) {
            tmp = queue.remove();
            closed.add(tmp);
            tmp.findNeighbours("LURD");
            for (Board n : tmp.neighbours
                 ) {
                depth = n.depth;
                if (n.equals(goal)) {
                    n.solutionSize = n.solutionPath.length();
                    System.out.print(n.solutionPath + "\n");
                    processedStatesNumber = closed.size();
                    long end = System.currentTimeMillis();
                    float elapsedTime = end - start;
                    file.saveSolution(n.solutionPath, n.solutionSize);
                    file.saveAdditionalInfo(n.solutionSize, visitedStatesNumber, processedStatesNumber,
                            depth, elapsedTime);
                    return true;
                }
                if (!queue.contains(n) && !closed.contains(n)) {
                    queue.add(n);
                    visitedStatesNumber++;
                }
            }
        }
        tmp.solutionSize = -1;
        return false;
    }
}

