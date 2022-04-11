import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchStop {
    TST tst = new TST();

    SearchStop(String stopsFile) {
        File file = new File(stopsFile);
        // load all stops into TST
        try {
            Scanner scanner = new Scanner(file);
            // skip headers
            scanner.nextLine();
            while (scanner.hasNext()) {
                String info = scanner.nextLine();
                String[] infoArr = info.split(",");
                String stopName = infoArr[2];
                String[] nameArr = stopName.split(" ");
                // remove these starting codes and move to end of name string
                if (nameArr[0].equals("FLAGSTOP") || nameArr[0].equals("NB") || nameArr[0].equals("EB")
                        || nameArr[0].equals("SB")
                        || nameArr[0].equals("WB")) {
                    String temp = nameArr[0] + " ";
                    stopName = stopName.replace(temp, "");
                    stopName += " " + temp;
                    infoArr[2] = stopName;
                }
                Stop stop = new Stop(infoArr);
                tst.put(stopName.toLowerCase(), stop);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Stops file not found");
        }
    }

    ArrayList<Stop> getSearchResults(String search) {
        ArrayList<Stop> searchResult = new ArrayList<Stop>();
        searchStops(tst.get(search), searchResult);
        return searchResult;
    }

    private void searchStops(Node stop, ArrayList<Stop> results) {
        if (stop == null)
            return;
        if (stop.left != null)
            searchStops(stop.left, results);
        if (stop.mid != null)
            searchStops(stop.mid, results);
        if (stop.left != null)
            searchStops(stop.left, results);
        if (stop.left == null && stop.mid == null && stop.right == null) {
            results.add(stop.stop);
            return;
        }
    }
}
