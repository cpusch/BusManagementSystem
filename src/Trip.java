
//class that creates trip object when used to store all trips
//when reading in files.
public class Trip {
    int arrivalInSeconds;
    // Array stores info on the trip
    String[] infoArr;

    Trip(String info) throws IllegalArgumentException {
        infoArr = info.split("\\s*,\\s*");

        // check arrival time in expected range
        String[] time = getArrivalTime().split(":");
        int hours = Integer.parseInt(time[0]);
        if (hours > 23 || hours < 0)
            throw new IllegalArgumentException("Arrival hours not in range of 0-23");

        int mins = Integer.parseInt(time[1]);
        if (mins > 59 || mins < 0)
            throw new IllegalArgumentException("Arrival mins not in range of 0-59");

        int seconds = Integer.parseInt(time[2]);
        if (seconds > 59 || seconds < 0)
            throw new IllegalArgumentException("Arrival seconds not in range of 0-59");

        this.arrivalInSeconds = seconds + (mins * 60) + (3600 * hours);

        // check departure time in expected range
        time = getArrivalTime().split(":");
        hours = Integer.parseInt(time[0]);
        if (hours > 23 || hours < 0)
            throw new IllegalArgumentException("Departure hours not in range of 0-23");

        mins = Integer.parseInt(time[1]);
        if (mins > 59 || mins < 0)
            throw new IllegalArgumentException("Departure mins not in range of 0-59");

        seconds = Integer.parseInt(time[2]);
        if (seconds > 59 || seconds < 0)
            throw new IllegalArgumentException("Departure seconds not in range of 0-59");
    }

    int getID() {
        return Integer.parseInt(infoArr[0]);
    }

    String getArrivalTime() {
        return infoArr[1];
    }

    String getDepartureTime() {
        return infoArr[2];
    }

    String getStopID() {
        return infoArr[3];
    }

    String getStopSequence() {
        return infoArr[4];
    }

    String getStopHeadsign() {
        return infoArr[5];
    }

    String getPickupType() {
        return infoArr[6];
    }

    String getDropOffType() {
        return infoArr[7];
    }

    String getShapeDistTraveled() {
        return infoArr[8];
    }

    int getArrivalInSeconds() {
        return arrivalInSeconds;
    }
}
