import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class DelayReducer extends Reducer<AirportWritableComparable, Text, Text, Text> {
    @Override
    protected void reduce(
            AirportWritableComparable key,
            Iterable<Text> values,
            Reducer<AirportWritableComparable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();
        String airportName = iterator.next().toString();
        if (iterator.hasNext()) {
            float min = Float.MAX_VALUE;
            float max = 0.f;
            float sum = 0.f;
            int count = 0;
            while (iterator.hasNext()) {
                float flightDelay = Float.parseFloat(iterator.next().toString());
                min = Math.min(min, flightDelay);
                max = Math.max(max, flightDelay);
                sum += flightDelay;
                count += 1;
            }
            float average = sum / count;
            context.write(
                    new Text("\nAirport: " + airportName),
                    new Text("\nMinimum delay time: " + min +
                            "\nMaximum delay time: " + max +
                            "\nAverage: " + average));
        }
    }
}
