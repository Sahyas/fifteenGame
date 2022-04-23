import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    public List<Board> neighbours = new ArrayList<>();

    public int[][] gameBoard;

    public int[] zero = new int[2];

    public FileManager fileManager = new FileManager();

    public String solutionPath = "";

    public int solutionSize = 0;

    public Board(Board newBoard){
        this.gameBoard = deepCopy(newBoard);
        solutionPath = String.valueOf(newBoard.solutionPath);
        this.neighbours = newBoard.neighbours;
        this.zero = newBoard.zero;
        this.fileManager = newBoard.fileManager;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == 0) {
                    zero[0] = i;
                    zero[1] = j;
                }
            }
        }
    }

    public Board(int[][] board){
        this.gameBoard = board;
    }

    public Board() {
        try {
            //INITIALIZE BOARD AND VALUES FROM FILE
            int counter = 2;
            int[] tmp = fileManager.initialFile();
            gameBoard = new int[tmp[0]][tmp[1]];
            for(int i = 0; i < tmp[0]; i++){
                for(int j = 0; j < tmp[1]; j++){
                    gameBoard[i][j] = tmp[counter];
                    counter++;
                }
            }
            //FIND ZERO PLACE
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard.length; j++) {
                    if (gameBoard[i][j] == 0) {
                        this.zero[0] = i;
                        this.zero[1] = j;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getBoardNumber(int x, int y) {
        return gameBoard[x][y];
    }

    public void printBoard(){
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                System.out.print(getBoardNumber(i, j));
                if(getBoardNumber(i, j)<10){
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder().
                append(gameBoard, board.gameBoard).isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37).append(gameBoard).toHashCode();
    }

    public Board move(String direction) {
        Board newBoard = new Board(this);
        switch (direction) {
            case "L":
                if (zero[1] != 0) {
                    newBoard.gameBoard[zero[0]][zero[1]] = newBoard.gameBoard[zero[0]][zero[1] - 1];
                    newBoard.gameBoard[zero[0]][zero[1] - 1] = 0;
                    newBoard.zero[1] = this.zero[1] - 1;
                    newBoard.solutionPath += "L";
                }
                break;
            case "R":
                if (zero[1] != gameBoard.length - 1) {
                    newBoard.gameBoard[zero[0]][zero[1]] = newBoard.gameBoard[zero[0]][zero[1] + 1];
                    newBoard.gameBoard[zero[0]][zero[1] + 1] = 0;
                    newBoard.zero[1] = this.zero[1] + 1;
                    newBoard.solutionPath += "R";
                }
                break;
            case "U":
                if (zero[0] != 0) {
                    newBoard.gameBoard[zero[0]][zero[1]] = newBoard.gameBoard[zero[0] - 1][zero[1]];
                    newBoard.gameBoard[zero[0] - 1][zero[1]] = 0;
                    newBoard.zero[0] = this.zero[0] - 1;
                    newBoard.solutionPath += "U";
                }
                break;
            case "D":
                if (zero[0] != gameBoard.length - 1) {
                    newBoard.gameBoard[zero[0]][zero[1]] = newBoard.gameBoard[zero[0] + 1][zero[1]];
                    newBoard.gameBoard[zero[0] + 1][zero[1]] = 0;
                    newBoard.zero[0] = this.zero[0] + 1;
                    newBoard.solutionPath += "D";
                }
                break;
        }
        return newBoard;
    }

    public boolean canBeMoved(String direction){
        if(zero[0] != gameBoard.length - 1 && direction.equals("D")){
            return true;
        }
        if(zero[0] != 0 && direction.equals("U")){
            return true;
        }
        if(zero[1] != gameBoard.length - 1 && direction.equals("R")){
            return true;
        }
        return zero[1] != 0 && direction.equals("L");
    }

    public void findNeighbours(){
        if(canBeMoved("U")){
            neighbours.add(move("U"));
        }
        if(canBeMoved("R")){
            neighbours.add(move("R"));
        }
        if(canBeMoved("D")){
            neighbours.add(move("D"));
        }
        if(canBeMoved("L")){
            neighbours.add(move("L"));
        }
    }

    public int[][] deepCopy(Board board){
        int[][] tab = new int[board.gameBoard.length][board.gameBoard.length];
        for(int i = 0; i < board.gameBoard.length; i++){
            System.arraycopy(board.gameBoard[i], 0, tab[i], 0, board.gameBoard.length);
        }
        return tab;
    }

    public void reverse() {
        Collections.reverse(neighbours);
    }
}
