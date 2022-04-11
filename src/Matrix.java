import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrix {
    int numNodes;
    int[][] matrix;

    Matrix(String stopTimeFiles, String transfersFiles) {
        File stopTimes = new File(stopTimeFiles);
        File transfers = new File(transfersFiles);
        matrix = new int[12479][12479];
        // set values in matrix to infinity
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j)
                    matrix[i][j] = 0;
                else
                    matrix[i][j] = 10000;
            }
        }

        // scan stop times file
        try {
            Scanner scanner = new Scanner(stopTimes);
            // skip headers
            scanner.nextLine();

            String temp1 = scanner.nextLine();
            while (scanner.hasNextLine()) {
                String temp2 = scanner.nextLine();
                String[] lineInfo1 = temp1.split(",");
                String[] lineInfo2 = temp2.split(",");
                int tripID1 = Integer.parseInt(lineInfo1[0]);
                int tripID2 = Integer.parseInt(lineInfo2[0]);
                int stopID1 = Integer.parseInt(lineInfo1[3]);
                int stopID2 = Integer.parseInt(lineInfo2[3]);

                if (tripID1 == tripID2) {
                    matrix[stopID1][stopID2] = 1;
                }

                temp1 = temp2;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

        // scan transfer file
        try {
            Scanner scanner = new Scanner(transfers);
            // skip headers
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                String[] lineInfo = temp.split(",");
                int stop1 = Integer.parseInt(lineInfo[0]);
                int stop2 = Integer.parseInt(lineInfo[1]);
                if (lineInfo.length == 3) {
                    matrix[stop1][stop2] = 2;
                } else {
                    matrix[stop1][stop2] = Integer.parseInt(lineInfo[3]) / 100;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
    }
}
