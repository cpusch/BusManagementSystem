import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String stop_timesPath = "C:/Users/User/Desktop/temp/BusManagementSystem/lib/stop_times.txt";
    static final String transfersPath = "C:/Users/User/Desktop/temp/BusManagementSystem/lib/transfers.txt";
    static final String stopsPath = "C:/Users/User/Desktop/temp/BusManagementSystem/lib/stops.txt";
    static Matrix matrix;
    static SearchStop stopsInfo;
    static Arrivals arrivals;

    public static void main(String args[]) {
        System.out.print("Loading...");
        matrix = new Matrix(stop_timesPath, transfersPath);
        stopsInfo = new SearchStop(stopsPath);
        try {
            arrivals = new Arrivals(stop_timesPath);
        } catch (FileNotFoundException e) {
            System.out.println("Stop times file not found");
        }
        System.out.println("Loading Complete!");
        boolean runProgram = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the bus management system!");
        while (runProgram) {
            System.out.println("\nOptions:");
            System.out.println("    1. Find the shortest path between two bus stops");
            System.out.println("    2. Search for a bus stop");
            System.out.println("    3. Searching for all trips within a given arrival time");
            System.out.println("    4. Exit program");
            System.out.print("Please select one of the following options by typing the corresponding number: ");
            int selection = 0;
            try {
                selection = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("\nEnter a valid number!");
                continue;
            }

            switch (selection) {
                case 1:
                    runShortestPath();
                    break;
                case 2:
                    runStopSearch();
                    break;
                case 3:
                    runTimeSearch();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    runProgram = false;
                    break;
                default:
                    System.out.println("\nPlease enter a valid number!");
            }
        }
        scanner.close();
    }

    public static void runShortestPath() {
        Scanner scanner = new Scanner(System.in);
        boolean loopControl = true, idOneControl = true, idTwoControl = true;
        int stopID1 = 0;
        int stopID2 = 0;
        while (loopControl) {
            // enter first stop id
            while (idOneControl) {
                System.out.print("Please enter the source stop ID or type 'exit' to return to selection: ");
                int selection = 0;
                String input = scanner.nextLine();
                if (input.toLowerCase().equals("exit")) {
                    loopControl = false;
                    idTwoControl = false;
                    break;
                }
                try {
                    selection = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("\nEnter a valid number!");
                    continue;
                }
                if (stopsInfo.stopsByID.get(selection) == null) {
                    System.out.println("\nEnter a valid stop ID!");
                    continue;
                } else {
                    stopID1 = selection;
                    break;
                }
            }
            // enter second stop id
            while (idTwoControl) {
                System.out.print("Please enter the destination stop ID or type 'home' to return to selection: ");
                int selection = 0;
                String input = scanner.nextLine();
                if (input.toLowerCase().equals("home")) {
                    loopControl = false;
                    break;
                }
                try {
                    selection = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("\nEnter a valid number!");
                    continue;
                }
                if (stopsInfo.stopsByID.get(selection) == null) {
                    System.out.println("\nEnter a valid stop ID!");
                    continue;
                } else {
                    stopID2 = selection;
                    break;
                }
            }

            ArrayList<Integer> path = ShortestPath.dijkstra(matrix.matrix, stopID1, stopID2);
            for (int i = 0; i < path.size(); i++) {
                System.out.println(
                        "[" + (i + 1) + "] " + stopsInfo.stopsByID.get(path.get(i)) + "[Stop " + path.get(i) + "]");
            }
        }
    }

    public static void runStopSearch() {
        boolean loopControl = true;

        while (loopControl) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your search keyword/s or type 'home' to return to selection: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("home")) {
                loopControl = false;
            } else {
                ArrayList<Stop> result = stopsInfo.getSearchResults(input);
                System.out.println("Your results are: ");
                if (result.size() == 0) {
                    System.out.println("    No results found.");
                } else {
                    System.out.println(
                            "stop_id   stop_code        stop_name                   stop_desc               stop_lat            stop_lon    zone_id      parent_station");
                    for (Stop s : result)
                        System.out.println(s.toString());
                }
            }
        }
    }

    public static void runTimeSearch() {
        boolean loopControl = true;

        while (loopControl) {
            Scanner scanner = new Scanner(System.in);
            ArrayList<Trip> trips;
            System.out.print(
                    "Please enter the date of bus arrival in the following format HH:MM:SS or type 'home' to return to selection: ");
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("home")) {
                loopControl = false;
                continue;
            }
            try {
                trips = arrivals.getTripsFromTime(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid time format entered");
                continue;
            }

            System.out.println("Following trips found for inputted time:");
            System.out.println(
                    "TripID      Arrival Time        Departure Time      StopID      Stop Sequence   Pickup Type     Shape Dist Traveled");
            for (Trip t : trips) {
                System.out.println(t.toString());
            }
        }
    }
}
