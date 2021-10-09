import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class DelayReducer extends Reducer<FlightWritableComparable, Text, Text, Text> {
    @Override
    protected void reduce(
            FlightWritableComparable key,
            Iterable<Text> values,
            Reducer<FlightWritableComparable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        super.reduce(key, values, context);
        Iterator<Text> iterator = values.iterator();
        float min = Integer.MAX_VALUE;
        float max = 0;
        float sum = 0;
        int count = 0;
        while (iterator.hasNext()) {
            ++count;
            float flightDelay = Float.parseFloat(iterator.next().toString());
            min = Math.min(min, flightDelay);
            max = Math.max(max, flightDelay);
            sum += flightDelay;
        }
    }
}