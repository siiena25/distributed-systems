mvn package
hadoop fs -rm -r output
hadoop fs -put ~/L_AIRPORT_ID.csv
hadoop fs -put ~/664600583_T_ONTIME_sample.csv
export HADOOP_CLASSPATH=target/hadoop-examples-1.0-SNAPSHOT.jar
hadoop MainApp 664600583_T_ONTIME_sample.csv L_AIRPORT_ID.csv output
spark-submit --class <Имя класса с main функцией> --master yarn-client --num-executors 3 <путь к jar файлу>
hadoop jar ./target/hadoop-examples-1.0-SNAPSHOT.jar App 664600583_T_ONTIME_sample.csv L_AIRPORT_ID.csv output
hadoop fs -copyToLocal output
exit 0