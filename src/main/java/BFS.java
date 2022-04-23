import java.util.*;

public class BFS {

    public BFS() {
    }

    public boolean search(Board startBoard, Board goal) {
        FileManager file = new FileManager();
        Queue<Board> queue = new LinkedList<>();
        Set<Board> closed = new HashSet<>();

        if (startBoard.equals(goal)) {
            return true;
        }
        queue.add(startBoard);
        while (!queue.isEmpty()) {
            Board tmp = queue.remove();
            closed.add(tmp);
            tmp.findNeighbours();
            for (int i = 0; i < tmp.neighbours.size(); i++) {
                if (tmp.neighbours.get(i).equals(goal)) {
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
                if (!queue.contains(tmp.neighbours.get(i)) && !closed.contains(tmp.neighbours.get(i))) {
                    queue.add(tmp.neighbours.get(i));
                }
            }
        }
        return false;
    }
}

