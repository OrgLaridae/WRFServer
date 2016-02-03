package WRF;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RunScript {
    private int iExitValue;
    private String sCommandString;

    public void runScript(String command) {
        sCommandString = command;
        CommandLine oCmdLine = CommandLine.parse(sCommandString);
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        oDefaultExecutor.setExitValue(0);
        try {
            iExitValue = oDefaultExecutor.execute(oCmdLine);
        } catch (ExecuteException e) {

            System.err.println("Execution failed.");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("permission denied.");
            e.printStackTrace();
        }
    }

//    public static void main(String args[]) {
//        RunScript runScript = new RunScript();
//
//        //initially change the namelist.wps and namelist.input files according to the parameters set
//        runScript.changeNamelistWPS(WRFEnvironment.getInputWPSPath(),WRFEnvironment.getStartDate(),WRFEnvironment.getEndDate(),WRFEnvironment.getMaxDom(),WRFEnvironment.getIntervalSeconds(),WRFEnvironment.getE_we(),WRFEnvironment.getE_sn(),WRFEnvironment.getGeo_data_path(),WRFEnvironment.getPrefix(),WRFEnvironment.getRef_lat(),WRFEnvironment.getRef_lon());
//        runScript.changeNamelipsInput(WRFEnvironment.getNamelistWRFPath(),WRFEnvironment.getRunDays(),WRFEnvironment.getRunHours(),WRFEnvironment.getStartYear(),WRFEnvironment.getStartMonth(),WRFEnvironment.getStartDay(),WRFEnvironment.getStartHour(),WRFEnvironment.getEndYear(),WRFEnvironment.getEndMonth(),WRFEnvironment.getEndtDay(),WRFEnvironment.getEndHour(),WRFEnvironment.getIntervalSeconds(),WRFEnvironment.getMaxDom(),WRFEnvironment.getE_we(),WRFEnvironment.getE_sn(),WRFEnvironment.getNum_metgrid_levels(),WRFEnvironment.getNum_metgrid_soil_levels());
//
//        //run the WRF using the shell scripts
//        runScript.runScript("sh /home/ruveni/IdeaProjects/DataAgent/src/main/java/org/mora/cep/AutomateWRF/autoauto.sh");
//    }

    //changes the namelist.wps property file according to the parameters set
    public void changeNamelistWPS(String filePath, String startDate, String endDate, int maxDom, long intervalSeconds, String e_we, String e_sn, String geog_data_path,String prefix,double ref_lat,double ref_lon,double pole_lat,double pole_lon,double stand_lon, String map_proj,double dx, double dy) {
        try {
            FileWriter fw=new FileWriter(filePath);
            Stream<String> namelistContent = Files.lines(Paths.get("/home/ruveni/IdeaProjects/Laridae/src/WRF/namelist.wps"));
            String[] namelistArray = namelistContent.collect(Collectors.toList()).toArray(new String[0]);
            //changes the parameters
            for (int i = 0; i < namelistArray.length; i++) {
                if (namelistArray[i].contains("start_date")) {
                    namelistArray[i] = " start_date = " + startDate + ",";
                } else if (namelistArray[i].contains("end_date")) {
                    namelistArray[i] = " end_date = " + endDate + ",";
                } else if (namelistArray[i].contains("max_dom")) {
                    namelistArray[i] = " max_dom = "+maxDom+",";
                } else if (namelistArray[i].contains("interval_seconds")) {
                    namelistArray[i] = " interval_seconds = "+intervalSeconds+",";
                } else if (namelistArray[i].contains("e_we")) {
                    namelistArray[i] = " e_we              =  "+e_we+",";
                } else if (namelistArray[i].contains("e_sn")) {
                    namelistArray[i] = " e_sn              =  "+e_sn+",";
                } else if (namelistArray[i].contains("geog_data_path")) {
                    namelistArray[i] = " geog_data_path = '"+geog_data_path+"',";
                }else if (namelistArray[i].contains("prefix")) {
                    namelistArray[i] = " prefix = '"+prefix+"',";
                }else if (namelistArray[i].contains("fg_name")) {
                    namelistArray[i] = " fg_name = '"+prefix+"',";
                }else if(namelistArray[i].contains("ref_lat")){
                    namelistArray[i] = " ref_lat = "+ref_lat+",";
                }else if(namelistArray[i].contains("ref_lon")){
                    namelistArray[i] = " ref_lon = "+ref_lon+",";
                }
                //writes the data to the file
                fw.write(namelistArray[i] + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //changes the namelist.input file according to the parameters set
    public void changeNamelipsInput(String filePath, int runDays,int runHours, String startYear, String startMonth, String startDay, String startHour, String endYear, String endMonth, String endDay, String endHour, long intervalSeconds, int maxDom, String e_we, String e_sn, int num_metgrid_levels, int num_metgrid_soil_levels, double dx, double dy) {
        try {
            FileWriter fw=new FileWriter(filePath);
            Stream<String> namelistContent = Files.lines(Paths.get("/home/ruveni/IdeaProjects/Laridae/src/WRF/namelist.input"));
            String[] namelistArray = namelistContent.collect(Collectors.toList()).toArray(new String[0]);
            //changes the parameters
            for (int i = 0; i < namelistArray.length; i++) {
                if (namelistArray[i].contains("max_dom")) {
                    namelistArray[i] = " max_dom = "+maxDom+",";
                } else if (namelistArray[i].contains("interval_seconds")) {
                    namelistArray[i] = " interval_seconds = "+intervalSeconds+",";
                } else if (namelistArray[i].contains("e_we")) {
                    namelistArray[i] = " e_we              =  "+e_we+",";
                } else if (namelistArray[i].contains("e_sn")) {
                    namelistArray[i] = " e_sn              =  "+e_sn+",";
                } else if(namelistArray[i].contains("run_days")){
                    namelistArray[i] = " run_days                            = "+runDays+",";
                }else if(namelistArray[i].contains("run_hours")){
                    namelistArray[i] = " run_hours                            = "+runHours+",";
                }else if(namelistArray[i].contains("start_year")){
                    namelistArray[i] = " start_year                            = "+startYear+",";
                }else if(namelistArray[i].contains("start_month")){
                    namelistArray[i] = " start_month                            = "+startMonth+",";
                }else if(namelistArray[i].contains("start_day")){
                    namelistArray[i] = " start_day                            = "+startDay+",";
                }else if(namelistArray[i].contains("start_hour")){
                    namelistArray[i] = " start_hour                            = "+startHour+",";
                }else if(namelistArray[i].contains("end_year")){
                    namelistArray[i] = " end_year                            = "+endYear+",";
                }else if(namelistArray[i].contains("end_month")){
                    namelistArray[i] = " end_month                            = "+endMonth+",";
                }else if(namelistArray[i].contains("end_day")){
                    namelistArray[i] = " end_day                            = "+endDay+",";
                }else if(namelistArray[i].contains("end_hour")){
                    namelistArray[i] = " end_hour                            = "+endHour+",";
                }else if(namelistArray[i].contains("num_metgrid_levels")){
                    namelistArray[i] = " num_metgrid_levels                            = "+num_metgrid_levels+",";
                }else if(namelistArray[i].contains("num_metgrid_soil_levels")){
                    namelistArray[i] = " num_metgrid_soil_levels                            = "+num_metgrid_soil_levels+",";
                }
                //writes the data to the file
                fw.write(namelistArray[i] + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}