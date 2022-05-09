import org.apache.commons.lang.time.StopWatch;

import java.util.*;

public class BFS {

    public BFS() {
    }

    public Board bfs2(Board startBoard, Board goal, String order) {
        Queue<Board> queue = new LinkedList<>();
        Set<Board> closed = new HashSet<>();
        if(startBoard.equals(goal)){
            return startBoard;
        }
        queue.add(startBoard);
        closed.add(startBoard);
        while(!queue.isEmpty()){
            Board tmp = queue.remove();
            tmp.findNeighbours(order);
            for (Board n : tmp.neighbours
            ) {
                if(n.equals(goal)){
                    n.processedStatesNumber = closed.size();
                    n.visitedStatesNumber = closed.size() + queue.size();
                    return n;
                }
                if(!closed.contains(n)){
                    queue.add(n);
                    closed.add(n);
                }
            }
        }
        return null;
    }

    public Board bfs(Board startBoard, Board goal, String order) {
        if (startBoard.equals(goal)) {
            return startBoard;
        }
        Queue<Board> queue = new LinkedList<>();
        Set<Board> closed = new HashSet<>();
        queue.add(startBoard);
        while (!queue.isEmpty()) {
            Board tmp = queue.remove();
            closed.add(tmp);
            tmp.findNeighbours(order);
            for (Board n : tmp.neighbours
                 ) {
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
        return null;
    }

    public Board bfs3(Board startBoard, Board goal, String order) {
        int deepest = 0;
        if(startBoard.equals(goal)){
            return startBoard;
        }
        Queue<Board> queue = new LinkedList<>();
        Set<Board> closed = new HashSet<>();
        queue.add(startBoard);
        closed.add(startBoard);
        while(!queue.isEmpty()){
            Board tmp = queue.remove();
            tmp.findNeighbours(order);
            for (Board n: tmp.neighbours
                 ) {
                if(goal.equals(n)){
                    n.processedStatesNumber = closed.size();
                    n.visitedStatesNumber = closed.size() + queue.size();
                    return n;
                }
                if(!closed.contains(n)){
                    queue.add(n);
                    closed.add(n);
                }
            }
        }
        return null;
    }
}

