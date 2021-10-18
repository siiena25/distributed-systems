import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportsPartitioner extends Partitioner<AirportWritableComparable, Text> {

    @Override
    public int getPartition(AirportWritableComparable flightWritableComparable, Text text, int numReduceTasks) {
        return (flightWritableComparable.getAirportId() & Integer.MAX_VALUE) % numReduceTasks;
    }
}
