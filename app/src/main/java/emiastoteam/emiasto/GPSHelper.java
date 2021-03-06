package emiastoteam.emiasto;

/**
 * Created by kzie on 2015-11-08.
 */
public class GPSHelper {

    public static double CalculatDistance(double latA, double lonA, double latT, double lonT)
    {
        double theta = lonA - lonT;
        double distance = Math.sin(degToRad(latA)) * Math.sin(degToRad(latT)) + Math.cos(degToRad(latA)) * Math.cos(degToRad(latT)) * Math.cos(degToRad(theta));
        distance = Math.acos(distance);
        distance = radToDeg(distance);
        distance = distance * 60 * 1.1515;
        distance = distance * 1.609344;
        return distance;
    }

    public double CalculateDist(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        double distance = 1;
        return distance;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private static double degToRad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    private static double radToDeg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}
