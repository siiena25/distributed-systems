mvn package
hadoop fs -rm -r output
hadoop fs -put ~/L_AIRPORT_ID.csv
hadoop fs -put ~/664600583_T_ONTIME_sample.csv
export HADOOP_CLASSPATH=target/hadoop-examples-1.0-SNAPSHOT.jar
spark-submit --class flights.MainApp --master yarn-client --num-executors 3 target/spark-examples-1.0-SNAPSHOT.jar
hadoop fs -copyToLocal output
exit 0