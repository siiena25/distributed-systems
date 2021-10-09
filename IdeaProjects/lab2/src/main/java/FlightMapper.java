import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, Text, FlightWritableComparable> {
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Context context)
            throws IOException, InterruptedException {
        String[] line = value.toString().split(",");
        if (key.get() > 0) {
            String airportId = line[0].replaceAll("\"", "");
            String code = line[1].replaceAll("\"", "");
            context.write(new FlightWritableComparable())
        }
        super.map(key, value, context);
    }
}
