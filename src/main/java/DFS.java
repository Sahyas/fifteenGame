import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DFS {
    public DFS() {

    }

    public boolean search(Board startBoard, Board goal) {
        FileManager file = new FileManager();
        LinkedList<Board> stack = new LinkedList<>();
        Set<Board> closed = new HashSet<>();
        if(startBoard.equals(goal)){
            return true;
        }
        stack.push(startBoard);
        while(!stack.isEmpty()){
            Board tmp = stack.pop();
            tmp.printBoard();
            closed.add(tmp);
            System.out.print("\n");
            tmp.findNeighbours();
            tmp.reverse();
            for (Board n : tmp.neighbours
            ) {
                if(n.equals(goal)) {
                    if(tmp.zero[1] == 3){
                        tmp.solutionPath += "R";
                    }
                    if(tmp.zero[1] == 2) {
                        tmp.solutionPath += "D";
                    }
                    tmp.solutionSize = tmp.solutionPath.length();
                    System.out.print(tmp.solutionPath + "\n");
                    file.saveToFile(tmp.solutionPath, tmp.solutionSize);
                    return true;
                }
                if(!closed.contains(n) && !stack.contains(n)) {
                    stack.push(n);
                }
                }
            }
        return false;
    }
}
