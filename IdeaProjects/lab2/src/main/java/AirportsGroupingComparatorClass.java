import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportsGroupingComparatorClass extends WritableComparator {

    public AirportsGroupingComparatorClass() {
        
    }

    @Override
    public int compare(WritableComparable firstItem, WritableComparable secondItem) {
        FlightWritableComparable first = (FlightWritableComparable) firstItem;
        FlightWritableComparable second = (FlightWritableComparable) secondItem;
        return first.compareByAirportId(first, second);
    }
}
