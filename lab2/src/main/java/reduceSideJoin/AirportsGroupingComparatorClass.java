import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportsGroupingComparatorClass extends WritableComparator {

    public AirportsGroupingComparatorClass() {
        super(AirportWritableComparable.class, true);
    }

    @Override
    public int compare(WritableComparable firstItem, WritableComparable secondItem) {
        AirportWritableComparable first = (AirportWritableComparable) firstItem;
        AirportWritableComparable second = (AirportWritableComparable) secondItem;
        return first.compareByAirportId(second);
    }
}
