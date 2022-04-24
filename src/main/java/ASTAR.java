import java.util.*;

public class ASTAR {

    public ASTAR(){

    }

    public Board astar(Board startBoard, Board goal, String heuristics){
        int f = 0;
        Queue<Board> queue = new PriorityQueue<>();
        Set<Board> closed = new HashSet<>();
        if(startBoard.equals(goal)){
            return startBoard;
        }
        queue.add(startBoard);
        while(!queue.isEmpty()){
            Board tmp = queue.remove();
            if(tmp.equals(goal)){
                tmp.solutionSize = tmp.solutionPath.length();
                tmp.processedStatesNumber = closed.size();
                tmp.visitedStatesNumber = closed.size() + queue.size();
                return tmp;
            }
            closed.add(tmp);
            tmp.findNeighbours("LURD");
            for (Board n : tmp.neighbours
                 ) {
                if(!closed.contains(n)){
                    if(Objects.equals(heuristics, "hamm")){
                        f = n.hammingMetric();
                    }
                    if(Objects.equals(heuristics, "manh")){
                        f = n.manhattan();
                    }
                    if(!queue.contains(n)){
                        n.priority = f;
                        queue.add(n);
                    }
                    else if(queue.element().priority > f){
                        queue.remove(n);
                        n.priority = f;
                        queue.add(n);
                    }
                }
            }
        }
        return null;
    }
}
