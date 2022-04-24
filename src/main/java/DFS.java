import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DFS {
    public DFS() {

    }

    public Board dfs(Board startBoard, Board goal, String order) {
        LinkedList<Board> stack = new LinkedList<>();
        Set<Board> closed = new HashSet<>();
        int depth = 0;
        if(startBoard.equals(goal)){
            return startBoard;
        }
        stack.push(startBoard);
        while(!stack.isEmpty()){
            Board tmp = stack.pop();
            closed.add(tmp);
            tmp.findNeighbours(order);
            tmp.reverse();
            if(depth > 22){
                continue;
            }
            for (Board n : tmp.neighbours
            ) {
                depth = n.depth;
                if(n.equals(goal)) {
                    n.solutionSize = n.solutionPath.length();
                    n.processedStatesNumber = closed.size();
                    n.visitedStatesNumber = closed.size() + stack.size();
                    return n;
                }
                if(!closed.contains(n) && !stack.contains(n)) {
                    stack.push(n);
                }
                }
            }
        return null;
    }
}
