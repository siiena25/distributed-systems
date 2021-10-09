import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportsMapper extends Mapper<LongWritable, Text, Text, FlightWritableComparable> {
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Mapper<LongWritable, Text, Text, FlightWritableComparable>.Context context)
            throws IOException, InterruptedException {
        String[] line = value.toString().split(",");
        if (key.get() > 0) {
            String airportId = line[0].replaceAll("\"", "");
            String code = line[1].replaceAll("\"", "");
            context.write(
                    new Text(code),
                    new FlightWritableComparable(Integer.parseInt(airportId), 0)
            );
        }
    }
}
