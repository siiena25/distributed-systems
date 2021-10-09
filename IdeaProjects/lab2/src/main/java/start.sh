mvn package
hadoop fs -rm -r output
hadoop fs -copyFromLocal 664600583_T_ONTIME_sample.csv
hadoop fs -copyFromLocal L_AIRPORT_ID.csv
export HADOOP_CLASSPATH=target/hadoop-examples-1.0-SNAPSHOT.jar
hadoop App L_AIRPORT_ID.csv 664600583_T_ONTIME_sample.csv output
hadoop fs -copyToLocal output
exit 0