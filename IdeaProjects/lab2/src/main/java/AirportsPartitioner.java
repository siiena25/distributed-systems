import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportsPartitioner extends Partitioner<FlightWritableComparable, Text> {

    @Override
    public int getPartition(FlightWritableComparable flightWritableComparable, Text text, int partitionsNumber) {
        return flightWritableComparable.getAirportId() % partitionsNumber;
    }
}
