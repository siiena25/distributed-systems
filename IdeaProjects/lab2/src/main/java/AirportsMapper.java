import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportsMapper extends Mapper<LongWritable, Text, FlightWritableComparable, Text> {
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Mapper<LongWritable, Text, FlightWritableComparable, Text>.Context context)
            throws IOException, InterruptedException {
        String[] line = value.toString().split(",");
        if (key.get() > 0) {
            String code = line[0].replaceAll("\"", "");
            String description = line[1].replaceAll("\"", "") + line[2].replaceAll("\"", "");
            context.write(
                    new FlightWritableComparable(Integer.parseInt(code), 0),
                    new Text(description)
            );
        }
    }
}
