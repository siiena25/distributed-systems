import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportsGroupingComparatorClass extends WritableComparator {
    @Override
    public int compare(FlightWritableComparable firstItem, FlightWritableComparable secondItem) {
        return Integer.compare(firstItem.getAirportId(), secondItem.getAirportId());
    }
}
