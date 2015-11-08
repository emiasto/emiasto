package emiastoteam.emiasto;

/**
 * Created by kzie on 2015-11-08.
 */
public class GPSHelper {

    public double CalculatDistance(double latA, double lonA, double latT, double lonT)
    {
        double theta = lonA - lonT;
        double distance = Math.sin(degToRad(latA)) * Math.sin(degToRad(latT)) + Math.cos(degToRad(latA)) * Math.cos(degToRad(latT)) * Math.cos(degToRad(theta));
        distance = Math.acos(distance);
        distance = radToDeg(distance);
        distance = distance * 60 * 1.1515;
        distance = distance * 1.609344;
        return distance;
    }

    private double degToRad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    private double radToDeg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}
