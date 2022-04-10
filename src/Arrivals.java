import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class Arrivals {
    static ArrayList<Trip> tripsByArival = new ArrayList<Trip>();
    static ArrayList<Trip> tripsByID = new ArrayList<Trip>();

    Arrivals(String stopTimesFile) throws FileNotFoundException {
        ArrayList<Trip> trips = new ArrayList<Trip>();

        File file = new File(stopTimesFile);

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
        // sort by trip ID
        trips.sort((o1, o2) -> ((Integer) o1.getID()).compareTo((Integer) o2.getID()));
        tripsByID = (ArrayList<Trip>) trips.clone();
        // sort by arrival time
        trips.sort((o1, o2) -> ((Integer) o1.arrivalInSeconds).compareTo((Integer) o2.arrivalInSeconds));
        tripsByArival = (ArrayList<Trip>) trips.clone();
    }

    /**
     * 
     * @param input
     * @return returns list of trips for the given arrival time
     */
    public ArrayList<Trip> getTripsFromTime(String input) {
        ArrayList<Trip> tripList = new ArrayList<Trip>();
        String[] time = input.split(":");
        if (time.length > 3 || time.length < 3)
            throw new IllegalArgumentException("Not valid time entered");
        int hours = Integer.parseInt(time[0]);
        if (hours > 23 || hours < 0)
            throw new IllegalArgumentException("Hours not in range of 0-23");

        int mins = Integer.parseInt(time[1]);
        if (mins > 59 || mins < 0)
            throw new IllegalArgumentException("Mins not in range of 0-59");

        int seconds = Integer.parseInt(time[2]);
        if (seconds > 59 || seconds < 0)
            throw new IllegalArgumentException("Seconds not in range of 0-59");

        // create temporary trip object to be able to search for the desired time.
        Trip searchTime = new Trip("0,0,0,0,0,0,0,0,0");
        searchTime.arrivalInSeconds = seconds + (mins * 60) + (3600 * hours);
        // comparator so that two trips can be compared by their travel time
        Comparator<Trip> compareTripTime = new Comparator<Trip>() {
            @Override
            public int compare(Trip a, Trip b) {
                return a.arrivalInSeconds - b.arrivalInSeconds;
            }
        };
        // index of first found trip with that time
        int index = Collections.binarySearch(tripsByArival, searchTime, compareTripTime);
        tripList.add(tripsByArival.get(index));

        boolean runSearch = true;
        int indexUp = index;
        int indexDown = index;
        // keeps looping and incrementing up and down to get all trips
        while (runSearch) {
            indexUp = index++;
            indexDown = index--;
            if (indexUp < tripsByArival.size() || indexDown > 0) {
                if (tripsByArival.get(indexUp).arrivalInSeconds == searchTime.arrivalInSeconds) {
                    tripList.add(tripsByArival.get(indexUp));
                } else if (tripsByArival.get(indexDown).arrivalInSeconds == searchTime.arrivalInSeconds) {
                    tripList.add(tripsByArival.get(indexDown));
                } else {
                    runSearch = false;
                }
            } else {
                runSearch = false;
            }
        }
        // comparator to sort list of trips by trip ID
        Comparator<Trip> compareTripID = new Comparator<Trip>() {
            @Override
            public int compare(Trip a, Trip b) {
                return a.getID() - b.getID();
            }
        };
        tripList.sort(compareTripID);
        return tripList;
    }
}