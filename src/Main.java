import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        String stop_timesPath = "C:/Users/User/Desktop/temp/BusManagementSystem/lib/stop_times.txt";
        try {
            Arrivals test = new Arrivals(stop_timesPath);
            ArrayList<Trip> trips = test.getTripsFromTime("21:10:00");
            for (Trip trip : trips) {
                System.out.println(trip.toString());
            }
            System.out.println(trips.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
}
