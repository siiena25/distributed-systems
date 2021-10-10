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
        Iterator<Text> iterator = values.iterator();
        if (iterator.hasNext()) {
            String airportName = iterator.next().toString();
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
                //context.write(new Text("\nflight delay: "), new Text(flightDelay + " " + min + " " + max + " " + count + " " + sum));
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
