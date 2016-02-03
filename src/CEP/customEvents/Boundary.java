package CEP.customEvents;

import java.io.Serializable;

public class Boundary implements Serializable{
    private double minLatitude;
    private double minLongitude;
    private double maxLatitude;
    private double maxLongitude;

    public Boundary(){
    }

    public Boundary(double minLatitude, double maxLatitude, double minLongitude, double maxLongitude){
        this.setMinLatitude(minLatitude);
        this.setMaxLatitude(maxLatitude);
        this.setMinLongitude(minLongitude);
        this.setMaxLongitude(maxLongitude);
    }

    public double getMinLatitude() {
        return minLatitude;
    }

    public void setMinLatitude(double minLatitude) {
        this.minLatitude = minLatitude;
    }

    public double getMinLongitude() {
        return minLongitude;
    }

    public void setMinLongitude(double minLongitude) {
        this.minLongitude = minLongitude;
    }

    public double getMaxLatitude() {
        return maxLatitude;
    }

    public void setMaxLatitude(double maxLatitude) {
        this.maxLatitude = maxLatitude;
    }

    public double getMaxLongitude() {
        return maxLongitude;
    }

    public void setMaxLongitude(double maxLongitude) {
        this.maxLongitude = maxLongitude;
    }
}
