import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportsGroupingComparatorClass extends WritableComparator {
    @Override
    public int compare(FlightWritableComparable a, FlightWritableComparable b) {
        return super.compare(a, b);
    }
}
