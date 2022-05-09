import java.util.*;

public class DFS {
    public DFS() {

    }

    public Board dfs(Board startBoard, Board goal, String order) {
        int deepest = 0;
        if (startBoard.equals(goal)) {
            return startBoard;
        }
        int counter = 0;
        LinkedList<Board> stack = new LinkedList<>();
        Set<Board> closed = new HashSet<>();
        stack.push(startBoard);
        counter++;
        while(!stack.isEmpty()){
            Board tmp = stack.pop();
            if(tmp.depth > 20){
                continue;
            }
            if(tmp.depth > deepest){
                deepest = tmp.depth;
            }
            closed.add(tmp);
            tmp.findNeighbours(order);
            tmp.reverse();
            for (Board n: tmp.neighbours
                 ) {
                if(goal.isEqual(n.getGameBoard())){
                    n.solutionSize = n.solutionPath.length();
                    n.processedStatesNumber = closed.size();
                    n.visitedStatesNumber = counter;
                    n.maxDepth = deepest;
                    return n;
                }
                if(!closed.contains(n) && !stack.contains(n)){
                    stack.push(n);
                    counter++;
                }
            }
        }
        startBoard.solutionSize = -1;
        startBoard.processedStatesNumber = closed.size();
        startBoard.visitedStatesNumber = counter;
        startBoard.maxDepth = deepest;
        return startBoard;
    }
}
