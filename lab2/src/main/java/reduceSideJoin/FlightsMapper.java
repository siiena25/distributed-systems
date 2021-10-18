import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightsMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {

    private static final int AIRPORT_ID = 14;
    private static final int AIRPORT_DELAY = 18;

    @Override
    protected void map(
            LongWritable key,
            Text value,
            Mapper<LongWritable, Text, AirportWritableComparable, Text>.Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split(",");
        if (key.get() > 0) {
            float delay;
            try {
                delay = Float.parseFloat(line[AIRPORT_DELAY]);
            } catch (NumberFormatException e) {
                delay = 0.f;
            }
            if (delay > 0.f) {
                int airportId = Integer.parseInt(line[AIRPORT_ID]);
                context.write(
                        new AirportWritableComparable(airportId, 1),
                        new Text(String.valueOf(delay))
                );
            }
        }
    }
}
