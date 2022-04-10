import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Arrivals {
    static ArrayList<Trip> trips;

    Arrivals(String stopFile) throws FileNotFoundException {
        trips = new ArrayList<Trip>();

        File file = new File(stopFile);

        try {
            Scanner scanner = new Scanner(file);

            // skip headers
            String line = scanner.nextLine();

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                trips.add(new Trip(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
        trips.sort((o1, o2) -> ((Integer) o1.getID()).compareTo((Integer) o2.getID()));
        trips.sort((o1, o2) -> ((Integer) o1.getArrivalInSeconds()).compareTo((Integer) o2.getArrivalInSeconds()));
    }
}