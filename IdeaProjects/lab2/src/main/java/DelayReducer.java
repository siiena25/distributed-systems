import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DelayReducer extends Reducer<FlightWritableComparable, Text, Text, Text> {
    @Override
    protected void reduce(
            FlightWritableComparable key,
            Iterable<Text> values,
            Reducer<FlightWritableComparable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        super.reduce(key, values, context);
    }
}
