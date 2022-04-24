import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {
    public FileManager() {

    }

    public int[] initialFile(String initialFile) throws FileNotFoundException {
        Scanner read = new Scanner(new File(initialFile));
        int rowNumber = read.nextInt();
        int columnNumber = read.nextInt();
        int[] tmp = new int[rowNumber * columnNumber + 2];
        tmp[0] = rowNumber;
        tmp[1] = columnNumber;
        for(int i = 2; i < rowNumber * columnNumber + 2; i++){
            tmp[i] = read.nextInt();
        }
        return tmp;
    }

    public void saveSolution(String solution, int number, String file)  {
        PrintWriter print;
        try {
            print = new PrintWriter(file);
            print.println(number);
            print.println(solution);
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveAdditionalInfo(int solutionSize, int visited, int processed, int maxDepth,
                                   float time, String file){
        PrintWriter print;
        try {
            print = new PrintWriter(file);
            print.println(solutionSize);
            print.println(visited);
            print.println(processed);
            print.println(maxDepth);
            print.println(time);
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
