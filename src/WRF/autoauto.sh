#!/bin/bash

#set the path to automated.sh
AUTO_PATH="src/WRF";

START=$(date +%s);

export NETCDF=/usr/local;
cd $AUTO_PATH;
chmod +x automated.sh;
chmod +x automateWRF.sh;
./automateWRF.sh [options] <<-END
32
0
END
./automated.sh [options] <<-END
2
END

END=$(date +%s);
DIFF=$(( $END - $START ));

echo "";
echo "WRF model completed successfully";
echo "runtime = $DIFF seconds";
echo "";