import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DFS {
    public DFS() {

    }

    public boolean dfs(Board startBoard, Board goal) {
        FileManager file = new FileManager();
        LinkedList<Board> stack = new LinkedList<>();
        Set<Board> closed = new HashSet<>();
        long start = System.currentTimeMillis();
        int visitedStatesNumber = 0;
        int processedStatesNumber;
        int depth = 0;
        if(startBoard.equals(goal)){
            return true;
        }
        stack.push(startBoard);
        while(!stack.isEmpty()){
            Board tmp = stack.pop();
            closed.add(tmp);
            tmp.findNeighbours("ULDR");
            tmp.reverse();
            if(depth > 22){
                continue;
            }
            for (Board n : tmp.neighbours
            ) {
                depth = n.depth;
                if(n.equals(goal)) {
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
                if(!closed.contains(n) && !stack.contains(n)) {
                    stack.push(n);
                    visitedStatesNumber++;
                }
                }
            }
        return false;
    }
}
