import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DelayReducer extends Reducer<FlightWritableComparable, Text, Text, Text> {
}
