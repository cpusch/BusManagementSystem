
public class Stop {
    int stop_id;
    int stop_code;
    String stop_name;
    String stop_desc;
    double stop_lat;
    double stop_lon;
    String zone_id;
    String stop_url;
    String location_type;
    Stop parentStation;

    Stop(String[] infoArr) {
        stop_id = Integer.parseInt(infoArr[0]);
        stop_code = Integer.parseInt(infoArr[1]);
        stop_name = infoArr[2];
        stop_desc = infoArr[3];
        stop_lat = Double.parseDouble(infoArr[4]);
        stop_lon = Double.parseDouble(infoArr[5]);
        zone_id = infoArr[6];
        stop_url = infoArr[7];
        location_type = infoArr[8];
    }

    public Stop getParent() {
        return parentStation;
    }

    public void setParent(Stop stop) {
        this.parentStation = stop;
    }
}
