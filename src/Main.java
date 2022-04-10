import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        String stop_timesPath = "/home/user/Semester2/BusManagementSystem/lib/stop_times.txt";
        try {
            Arrivals test = new Arrivals(stop_timesPath);
            ArrayList<Trip> trips = test.getTripsFromTime("12:00:00");
            for (Trip trip : trips) {
                System.out.println(trip.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
}
