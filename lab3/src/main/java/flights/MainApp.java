package flights;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Map;

public class MainApp {

    private final static String QUOTES = "\"";
    private final static String EMPTY_STRING = "";

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
        JavaRDD<String> airportsFile = removeQuotes(sc.textFile(airports));

        Map<Integer, String> airportsDataMap = airportsFile.mapToPair(
                line -> getAirportPairs(line)
        );
        
    }

    private static Tuple2<Object, Object> getAirportPairs(String line) {
        int indexOf
    }

    private static JavaRDD<String> removeQuotes(JavaRDD<String> textFile) {
        return textFile.map(
                line -> line.replaceAll(QUOTES, EMPTY_STRING)
        );
    }
}
