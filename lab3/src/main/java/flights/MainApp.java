package flights;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class MainApp {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        if (args.length != 3) {
            System.err.println("Usage: App <flights> <airports> <output>");
            System.exit(-1);
        }

        String flights = args[0];
        String airports = args[1];

        JavaRDD<String> flightsFile = removeQuotes(sc.textFile(flights));
        JavaRDD<String> airportsFile = sc.textFile(airports);
        
    }

    private static JavaRDD<String> removeQuotes(JavaRDD<String> textFile) {
    }
}
