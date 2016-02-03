import CEP.customEvents.Boundary;
import WRF.NamelistCalc;
import WRF.RunScript;
import WRF.WRFEnvironment;

import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by vidu on 2/3/16.
 */
public class Main {


    static Boundary boundary;
    static RunScript runScript;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(4444);
            while (true) {
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                ArrayList<Boundary> boundaryArray = (ArrayList<Boundary>) ois.readObject();
                System.out.println(boundaryArray);
                if (boundaryArray != null) {
                    runWRF(boundaryArray);
                }
                is.close();
                s.close();
                ss.close();

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void runWRF(ArrayList<Boundary> boundaryArray) {
        ArrayList<Double> clusterTimeArray = new ArrayList<>();
        WRFEnvironment wrfEnvironment = new WRFEnvironment();
        long start = System.currentTimeMillis();
        for (int i = 0; i < boundaryArray.size(); i++) {
            long startCluster = System.currentTimeMillis();
            boundary = boundaryArray.get(i);
            //System.out.println(boundary.getMinLatitude()+" "+boundary.getMaxLatitude()+" "+boundary.getMinLongitude()+" "+boundary.getMaxLongitude());
            wrfEnvironment.setRef_lat(NamelistCalc.get_refLat(boundary));
            wrfEnvironment.setRef_lon(NamelistCalc.get_refLon(boundary));

//            wrfEnvironment.setPole_lat(NamelistCalc.get_pole_lat(boundary));
//            wrfEnvironment.setPole_lon(NamelistCalc.get_pole_lon());
//            wrfEnvironment.setStand_lon(NamelistCalc.get_standard_lon(boundary));
//            wrfEnvironment.setMap_proj("lat-lon");
//            wrfEnvironment.setDx(0.26);//default : 30000
//            wrfEnvironment.setDy(0.26);
//            wrfEnvironment.setResolution(0.26);
            wrfEnvironment.setE_sn(String.valueOf(NamelistCalc.get_e_ns(boundary, (int)wrfEnvironment.getResolution())));
            wrfEnvironment.setE_we(String.valueOf(NamelistCalc.get_e_we(boundary, (int)wrfEnvironment.getResolution())));

            /*
            e_we = 74, 112,
e_sn = 61, 97,
geog_data_res = '10m','2m',
dx = 0.15,
dy = 0.15,
map_proj = 'lat-lon',
pole_lat = 90,
pole_lon = 180,
ref_lat = 53.5,
ref_lon = -4.0,
stand_lon = 0.0,

dx = 0.2695,
 dy = 0.2713,
 map_proj = 'lat-lon',
 ref_lat = 45.28,
 ref_lon = -131.66,
 truelat1  =  30.0,
 truelat2  =  60.0,
 stand_lon = 131.66,
 pole_lat = 44.72,
 pole_lon = 180.0,
             */
//            wrfEnvironment.setE_we("74");
//            wrfEnvironment.setE_sn("61");
//            wrfEnvironment.setDx(0.2695);
//            wrfEnvironment.setDy(0.2695);
//            wrfEnvironment.setMap_proj("lat-lon");
//            wrfEnvironment.setPole_lat(44.72);
//            wrfEnvironment.setPole_lon(180);
//            wrfEnvironment.setRef_lat(45.28);
//            wrfEnvironment.setRef_lon(-131.66);
//            wrfEnvironment.setStand_lon(131.66);

//            wrfEnvironment.setE_sn(String.valueOf(NamelistCalc.get_e_ns_latlon(boundary,wrfEnvironment.getResolution())));
//            wrfEnvironment.setE_we(String.valueOf(NamelistCalc.get_e_we_latlon(boundary,wrfEnvironment.getResolution())));
//

            runScript = new RunScript();

            //initially change the namelist.wps and namelist.input files according to the parameters set
            runScript.changeNamelistWPS(wrfEnvironment.getInputWPSPath(), wrfEnvironment.getStartDate(), wrfEnvironment.getEndDate(), wrfEnvironment.getMaxDom(), wrfEnvironment.getIntervalSeconds(), wrfEnvironment.getE_we(), wrfEnvironment.getE_sn(), wrfEnvironment.getGeo_data_path(), wrfEnvironment.getPrefix(), wrfEnvironment.getRef_lat(), wrfEnvironment.getRef_lon(), wrfEnvironment.getPole_lat(), wrfEnvironment.getPole_lon(), wrfEnvironment.getStand_lon(), wrfEnvironment.getMap_proj(), wrfEnvironment.getDx(), wrfEnvironment.getDy());
            runScript.changeNamelipsInput(wrfEnvironment.getNamelistWRFPath(), wrfEnvironment.getRunDays(), wrfEnvironment.getRunHours(), wrfEnvironment.getStartYear(), wrfEnvironment.getStartMonth(), wrfEnvironment.getStartDay(), wrfEnvironment.getStartHour(), wrfEnvironment.getEndYear(), wrfEnvironment.getEndMonth(), wrfEnvironment.getEndtDay(), wrfEnvironment.getEndHour(), wrfEnvironment.getIntervalSeconds(), wrfEnvironment.getMaxDom(), wrfEnvironment.getE_we(), wrfEnvironment.getE_sn(), wrfEnvironment.getNum_metgrid_levels(), wrfEnvironment.getNum_metgrid_soil_levels(), wrfEnvironment.getDx(), wrfEnvironment.getDy());

            //run the WRF using the shell scripts
            runScript.runScript("sh /home/ruveni/IdeaProjects/Laridae/src/WRF/autoauto.sh");
            long endCluster = System.currentTimeMillis();
            double clusterTime = (endCluster - startCluster) / 1000.0;
            clusterTimeArray.add(clusterTime);

            //find a file name with wrfout_d01_2006-12-19_12:00:00
            File dir = new File(wrfEnvironment.getTest_em_real_path());
            File[] foundFiles = dir.listFiles((dir1, name) -> {
                return name.startsWith("wrfout_");
            });

            if (foundFiles.length >= 1) {
                File oldName = new File(foundFiles[0].getPath());
                File newName = new File(wrfEnvironment.getTest_em_real_path() + "wrfOut" + (i + 1));
                if (oldName.renameTo(newName)) {
                    System.out.println("renamed");
                } else {
                    System.out.println("Error");
                }
            }
        }
        long end = System.currentTimeMillis();
//        wrfTime = (end - start) / 1000.0;
//        System.out.println("CEP Time : " + cepTime + " seconds");
//        System.out.println("ML Time : " + mlTime + " seconds");
//        System.out.println("WRF Time : " + wrfTime + " seconds");
        for (int i = 0; i < clusterTimeArray.size(); i++) {
            System.out.println("Cluster " + (i + 1) + " : " + clusterTimeArray.get(i));
        }

//        System.out.println("Total Time : " + (cepTime + mlTime + wrfTime) + " seconds");
    }
}
