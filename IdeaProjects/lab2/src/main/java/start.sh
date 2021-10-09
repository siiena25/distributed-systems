mvn package
hadoop fs -rm -r output
hadoop App L_AIRPORT_ID.csv 664600583_T_ONTIME_sample.csv output
rm -rf output
hadoop fs -copyToLocal output
exit 0