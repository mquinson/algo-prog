#! /bin/bash

DATA_DIR="./data"
DATA_FILES="data1.txt data2.txt data3.txt data4.txt data5.txt data6.txt"
CLASS_PATH="./solution/bin"

for DATA_FILE in ${DATA_FILES}
do
	echo "+ Execution times for "${DATA_DIR}/${DATA_FILE}	 
	
	COUNTER=0
	while [ ${COUNTER} -lt 10 ]; 
	do
		RES=`java -cp ${CLASS_PATH} puzzle.Main ${DATA_DIR}/${DATA_FILE} | tail -6 | head -1  | sed 's/[^0-9:.]*//' | sed 's/[^0-9:.]*[0-9]*[^0-9:.]*$//' | sed 's/.*\.0\([0-9]*\)$/\1/'`
		echo -n ${RES}ms " "
		let COUNTER=COUNTER+1
	done
	echo 
done
