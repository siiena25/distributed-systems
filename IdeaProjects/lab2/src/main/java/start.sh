mvn package
hadoop fs -copyFromLocal 664600583_T_ONTIME_sample.csv
export HADOOP_CLASSPATH=target/hadoop-examples-1.0-SNAPSHOT.jar
hadoop App 664600583_T_ONTIME_sample.csv 