import java.util.*;

public class ASTAR {

    public ASTAR(){

    }

    public boolean astar(Board startBoard, Board goal, String heuristics){
        long start = System.currentTimeMillis();
        int f = 0;
        FileManager file = new FileManager();
        Queue<Board> queue = new PriorityQueue<>();
        Set<Board> closed = new HashSet<>();
        int visitedStatesNumber = 0;
        int processedStatesNumber;
        int depth = 0;
        if(startBoard.equals(goal)){
            return true;
        }
        queue.add(startBoard);
        while(!queue.isEmpty()){
            Board tmp = queue.remove();
            if(tmp.equals(goal)){
                long end = System.currentTimeMillis();
                float elapsedTime = end - start;
                tmp.solutionSize = tmp.solutionPath.length();
                System.out.print(tmp.solutionPath + "\n");
                processedStatesNumber = closed.size();
                file.saveSolution(tmp.solutionPath, tmp.solutionSize);
                file.saveAdditionalInfo(tmp.solutionSize, visitedStatesNumber, processedStatesNumber,
                        depth, elapsedTime);
                return true;
            }
            closed.add(tmp);
            tmp.findNeighbours("LURD");
            for (Board n : tmp.neighbours
                 ) {
                depth = n.depth;
                if(!closed.contains(n)){
                    if(Objects.equals(heuristics, "hamm")){
                        f = n.depth + n.hammingMetric(n);
                    }
                    if(Objects.equals(heuristics, "manh")){
                        f = n.depth + n.manhattan(n);
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
        return false;
    }
}
