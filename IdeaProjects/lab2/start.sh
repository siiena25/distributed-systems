mvn package
hadoop fs -rm -r output
hadoop fs -put ~/L_AIRPORT_ID.csv
hadoop fs -put ~/664600583_T_ONTIME_sample.csv
export HADOOP_CLASSPATH=target/hadoop-examples-1.0-SNAPSHOT.jar
hadoop App 664600583_T_ONTIME_sample.csv L_AIRPORT_ID.csv output
hadoop jar ./target/hadoop-examples-1.0-SNAPSHOT.jar App 664600583_T_ONTIME_sample.csv L_AIRPORT_ID.csv output
hadoop fs -rm -r output
hadoop fs -copyToLocal output
exit 0