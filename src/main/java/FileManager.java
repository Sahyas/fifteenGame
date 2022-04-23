import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {
    public static String INITIAL_FILE = "uklad_poczatkowy.txt";

    public FileManager() {

    }

    public int[] initialFile() throws FileNotFoundException {
        Scanner read = new Scanner(new File(INITIAL_FILE));
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
}
