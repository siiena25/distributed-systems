mvn package
hadoop fs -rm -r output
hadoop fs -put ~/L_AIRPORT_ID.csv
hadoop fs -put ~/664600583_T_ONTIME_sample.csv
spark-submit --class flights.MainApp 664600583_T_ONTIME_sample.csv L_AIRPORT_ID.csv output --master yarn-client --num-executors 3 ./target/spark-examples-1.0-SNAPSHOT.jar
hadoop fs -copyToLocal output
exit 0