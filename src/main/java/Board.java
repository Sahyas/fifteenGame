import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board implements Comparable<Board> {

    public List<Board> neighbours = new ArrayList<>();

    public int[][] gameBoard;

    public int[] zero = new int[2];

    public FileManager fileManager = new FileManager();

    public String solutionPath = "";

    public int solutionSize;

    public int depth;

    public int priority;

    public int visitedStatesNumber;

    public int processedStatesNumber;

    public String previousMove;

    public int maxDepth;

    public Board(Board newBoard, String move){
        this.gameBoard = deepCopy(newBoard);
        solutionPath = String.valueOf(newBoard.solutionPath);
        this.priority = 0;
        this.previousMove = move;
        this.zero[0] = newBoard.zero[0];
        this.zero[1] = newBoard.zero[1];
    }

    public Board(int[][] board){
        this.depth = 0;
        this.solutionSize = 0;
        this.previousMove = "none";
        this.gameBoard = board;
    }

    public Board(String file) {
        try {
            //INITIALIZE BOARD AND VALUES FROM FILE
            this.previousMove = "none";
            int counter = 2;
            int[] tmp = fileManager.initialFile(file);
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

        return new EqualsBuilder().append(gameBoard, board.gameBoard).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(gameBoard).toHashCode();
    }

    public Board move(String direction) {
        Board newBoard = new Board(this, direction);
        switch (direction) {
            case "L":
                    newBoard.gameBoard[zero[0]][zero[1]] = newBoard.gameBoard[zero[0]][zero[1] - 1];
                    newBoard.gameBoard[zero[0]][zero[1] - 1] = 0;
                    newBoard.zero[1] = this.zero[1] - 1;
                    newBoard.solutionPath += "L";
                    newBoard.depth = this.depth + 1;
                break;
            case "R":
                    newBoard.gameBoard[zero[0]][zero[1]] = newBoard.gameBoard[zero[0]][zero[1] + 1];
                    newBoard.gameBoard[zero[0]][zero[1] + 1] = 0;
                    newBoard.zero[1] = this.zero[1] + 1;
                    newBoard.solutionPath += "R";
                    newBoard.depth = this.depth + 1;
                break;
            case "U":
                    newBoard.gameBoard[zero[0]][zero[1]] = newBoard.gameBoard[zero[0] - 1][zero[1]];
                    newBoard.gameBoard[zero[0] - 1][zero[1]] = 0;
                    newBoard.zero[0] = this.zero[0] - 1;
                    newBoard.solutionPath += "U";
                    newBoard.depth = this.depth + 1;
                break;
            case "D":
                    newBoard.gameBoard[zero[0]][zero[1]] = newBoard.gameBoard[zero[0] + 1][zero[1]];
                    newBoard.gameBoard[zero[0] + 1][zero[1]] = 0;
                    newBoard.zero[0] = this.zero[0] + 1;
                    newBoard.solutionPath += "D";
                    newBoard.depth = this.depth + 1;
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

    public void findNeighbours(String moveOrder){
        for(int i = 0; i < 4; i++) {
            if (canBeMoved(String.valueOf(moveOrder.charAt(i))) && IsNotGoingBack(moveOrder.charAt(i))) {
                neighbours.add(move(String.valueOf(moveOrder.charAt(i))));
            }
        }
    }

    public boolean IsNotGoingBack(char direction) {
        return (!previousMove.equals("L") || direction != 'R') &&
                (!previousMove.equals("U") || direction != 'D') &&
                (!previousMove.equals("R") || direction != 'L') &&
                (!previousMove.equals("D") || direction != 'U');
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

    public int hammingMetric(){
        int hamming = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (this.gameBoard[i - 1][j - 1] == (i - 1) * 4 + j || this.gameBoard[i - 1][j - 1] == 0) {
                    continue;
                } else {
                    hamming++;
                }
            }
        }
        hamming += this.depth;
        return hamming;
    }

    public int manhattan() {
        int distance = 0;
        int currentRow;
        int currentColumn;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if(this.gameBoard[i][j] != 0 && this.gameBoard[i][j] != i * 4 + j + 1){
                    int boardValue = this.gameBoard[i][j] -1;
                    currentRow = i;
                    currentColumn = j;
                    int correctRow = boardValue / 4;
                    int correctColumn = boardValue % 4 - 1;
                    distance += Math.abs(correctRow - currentRow) + Math.abs(correctColumn - currentColumn);
                }
            }
        }
        distance += this.depth;
        return distance;
    }

    public boolean isEqual(int[][] board) {
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard.length; j++){
                if(gameBoard[i][j] != board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int compareTo(Board o) {
        return Integer.compare(this.priority, o.priority);
        }
}
