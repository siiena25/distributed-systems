import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportsGroupingComparatorClass extends WritableComparator {
    @Override
    public int compare(WritableComparable firstItem, WritableComparable secondItem) {
        if (firstItem.getAirportId() > secondItem.getAirportId()) {
            return 1;
        } else if (firstItem.getAirportId() < secondItem.getAirportId()) {
            return -1;
        } else {
            return 0;
        }
    }
}
