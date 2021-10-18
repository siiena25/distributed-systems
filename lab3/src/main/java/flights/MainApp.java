package flights;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Map;

public class MainApp {

    private final static String QUOTES = "\"";
    private final static String EMPTY_STRING = "";
    private final static String COMMA = ",";

    private final static int COLUMN_NUMBER_OF_ORIGIN_AIRPORT_ID = 11;
    private final static int COLUMN_NUMBER_OF_DEST_AIRPORT_ID = 14;
    private final static int COLUMN_NUMBER_OF_DELAY_ID = 18;
    private final static int COLUMN_NUMBER_OF_IS_CANCELLED_ID = 19;

    private static final float ZERO_DELAY = 0.f;

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

        Map<Integer, String> flightsDataMap = flightsFile.mapToPair(MainApp::getFlightPairs).collectAsMap();
        Map<Integer, String> airportsDataMap = airportsFile.mapToPair(MainApp::getAirportPairs).collectAsMap();

        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(airportsDataMap);
        
    }

    private static Tuple2<Tuple2<Integer, Integer>, String> getFlightPairs(String line) {
        String[] columnsOfFlightsTable = line.split(COMMA);
        int originAirportId = Integer.parseInt(columnsOfFlightsTable[COLUMN_NUMBER_OF_ORIGIN_AIRPORT_ID]);
        int destAirportId = Integer.parseInt(columnsOfFlightsTable[COLUMN_NUMBER_OF_DEST_AIRPORT_ID]);
        float delay = saveGetDelay(columnsOfFlightsTable[COLUMN_NUMBER_OF_DELAY_ID]);
        boolean isCancelled = columnsOfFlightsTable[COLUMN_NUMBER_OF_IS_CANCELLED_ID].isEmpty();
    }

    private static float saveGetDelay(String line) {
        try {
            return Float.parseFloat(line);
        } catch (NumberFormatException e) {
            return ZERO_DELAY;
        }
    }

    private static Tuple2<Integer, String> getAirportPairs(String line) {
        int indexOfComma = line.indexOf(COMMA);
        Integer airportID = Integer.valueOf(line.substring(0, indexOfComma));
        String airportName = line.substring(indexOfComma + 1);
        return new Tuple2<>(airportID, airportName);
    }

    private static JavaRDD<String> removeQuotes(JavaRDD<String> textFile) {
        return textFile.map(
                line -> line.replaceAll(QUOTES, EMPTY_STRING)
        );
    }
}
