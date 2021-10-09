import org.apache.hadoop.mapreduce.Partitioner;

import javax.xml.soap.Text;

public class AirportsPartitioner extends Partitioner<FlightWritableComparable, Text> {

    @Override
    public int getPartition(FlightWritableComparable flightWritableComparable, Text text, int partitionsNumber) {
        return flightWritableComparable.getAirportId();
    }
}
