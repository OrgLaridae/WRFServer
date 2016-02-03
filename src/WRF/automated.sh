#!/bin/bash

#Automated runnig of WRF model

#execute like below
#$ ./automated.sh [options] <<-END
#32
#0
#2
#END

START=$(date +%s);

#set paths to following directories
WPS_PATH="/home/ruveni/Data/TestWRF/WPS";
WRF_EMREAL_PATH="/home/ruveni/Data/TestWRF/WRFV3/test/em_real";

#set path to data
WRF_DATA="/home/ruveni/Data/TestWRF/WPS/ungrib/Colorado/";

START=$(date +%s);

#WPS
echo "Moving to WPS directory";
#cd /home/chamil/Playground/WPS;
cd $WPS_PATH;
ls;
echo "configuring WPS...";
./configure;
echo "compiling WPS..";
./compile;

#WPS geogrid
echo "Running geogrid.exe";
./geogrid.exe;

#WPS ungrib
echo "linking Vtable";
ln -sf ungrib/Variable_Tables/Vtable.NAM Vtable;

echo "linking data path";
#./link_grib.csh /home/chamil/Playground/WPS/ungrib/Colorado/;
./link_grib.csh $WRF_DATA;

echo "Running ungrib.exe";
./ungrib.exe;

#WPS metgrid
echo "Running WPS metgrid.exe";
./metgrid.exe;

#WRF final
echo "moving to WRF em_real";
cd $WRF_EMREAL_PATH;
ls;

echo "linking intermediate files";
#ln -sf /home/chamil/Playground/WPS/met_em.d0* .;
ln -sf $WPS_PATH/met_em.d0* .;

echo "running real.exe";
./real.exe;

echo "running WRF.exe";
./wrf.exe;

END=$(date +%s);
DIFF=$(( $END - $START ));

echo "";
echo "WRF model completed successfully";
echo "runtime = $DIFF seconds";
echo "";