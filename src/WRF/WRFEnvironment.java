package WRF;

/**
 * Created by ruveni on 1/25/16.
 */
public class WRFEnvironment {
    private String inputWPSPath="/home/ruveni/Data/TestWRF/WPS/namelist.wps";
    private String startDate="'2006-12-19_12:00:00'";
    private String endDate="'2006-12-20_12:00:00'";
    private int maxDom=1;
    private long intervalSeconds=10800;
    private String e_we="74";
    private String e_sn="61";
    private String geo_data_path="/home/ruveni/Data/TestWRF/WPS/geogrid/Data/geog_minimum";
    private String prefix="FILE";//default FILE
    private double ref_lat=39.00;
    private double ref_lon=-105.00;
    private double pole_lat=0.0;
    private double pole_lon=0.0;
    private double stand_lon=0.0;
    private String map_proj="lambert";
    private double dx=30000;
    private double dy=30000;

    //namelist.input parameters to be changed
    private String namelistWRFPath="/home/ruveni/Data/TestWRF/WRFV3/test/em_real/namelist.input";
    //int runDays,int runHours, String startYear, String startMonth, String startDay, String startHour, String endYear, String endMonth, String endDay, String endHour, long intervalSeconds, int maxDom, int e_we, int e_sn, int num_metgrid_levels, int num_metgrid_soil_levels) {
    private int runDays=0;
    private int runHours=24;
    private String startYear="2006";
    private String startMonth="12";
    private String startDay="19";
    private String startHour="12";
    private String endYear="2006";
    private String endMonth="12";
    private String endtDay="20";
    private String endHour="12";
    private int num_metgrid_levels=40;
    private int num_metgrid_soil_levels=4;

    private double resolution=30; //km

    private String test_em_real_path="/home/ruveni/Data/TestWRF/WRFV3/test/em_real/";


    public String getInputWPSPath() {
        return inputWPSPath;
    }

    public void setInputWPSPath(String inputWPSPath) {
        this.inputWPSPath = inputWPSPath;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMaxDom() {
        return maxDom;
    }

    public void setMaxDom(int maxDom) {
        this.maxDom = maxDom;
    }

    public long getIntervalSeconds() {
        return intervalSeconds;
    }

    public void setIntervalSeconds(long intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }

    public String getE_we() {
        return e_we;
    }

    public void setE_we(String e_we) {
        this.e_we = e_we;
    }

    public String getE_sn() {
        return e_sn;
    }

    public void setE_sn(String e_sn) {
        this.e_sn = e_sn;
    }

    public String getGeo_data_path() {
        return geo_data_path;
    }

    public void setGeo_data_path(String geo_data_path) {
        this.geo_data_path = geo_data_path;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public double getRef_lat() {
        return ref_lat;
    }

    public void setRef_lat(double ref_lat) {
        this.ref_lat = ref_lat;
    }

    public double getRef_lon() {
        return ref_lon;
    }

    public void setRef_lon(double ref_lon) {
        this.ref_lon = ref_lon;
    }

    public String getNamelistWRFPath() {
        return namelistWRFPath;
    }

    public void setNamelistWRFPath(String namelistWRFPath) {
        this.namelistWRFPath = namelistWRFPath;
    }

    public int getRunDays() {
        return runDays;
    }

    public void setRunDays(int runDays) {
        this.runDays = runDays;
    }

    public int getRunHours() {
        return runHours;
    }

    public void setRunHours(int runHours) {
        this.runHours = runHours;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getEndtDay() {
        return endtDay;
    }

    public void setEndtDay(String endtDay) {
        this.endtDay = endtDay;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public int getNum_metgrid_levels() {
        return num_metgrid_levels;
    }

    public void setNum_metgrid_levels(int num_metgrid_levels) {
        this.num_metgrid_levels = num_metgrid_levels;
    }

    public int getNum_metgrid_soil_levels() {
        return num_metgrid_soil_levels;
    }

    public void setNum_metgrid_soil_levels(int num_metgrid_soil_levels) {
        this.num_metgrid_soil_levels = num_metgrid_soil_levels;
    }

    public double getResolution() {
        return resolution;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }

    public double getPole_lat() {
        return pole_lat;
    }

    public void setPole_lat(double pole_lat) {
        this.pole_lat = pole_lat;
    }

    public double getPole_lon() {
        return pole_lon;
    }

    public void setPole_lon(double pole_lon) {
        this.pole_lon = pole_lon;
    }

    public double getStand_lon() {
        return stand_lon;
    }

    public void setStand_lon(double stand_lon) {
        this.stand_lon = stand_lon;
    }

    public String getMap_proj() {
        return map_proj;
    }

    public void setMap_proj(String map_proj) {
        this.map_proj = map_proj;
    }

    public String getTest_em_real_path() {
        return test_em_real_path;
    }

    public void setTest_em_real_path(String test_em_real_path) {
        this.test_em_real_path = test_em_real_path;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}