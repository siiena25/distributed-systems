import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportsMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Mapper<LongWritable, Text, AirportWritableComparable, Text>.Context context)
            throws IOException, InterruptedException {
        String[] line = value.toString().split(",[\"]");
        if (key.get() > 0) {
            String code = line[0].replaceAll("\"", "");
            String description = line[1].replaceAll("\"", "");
            context.write(
                    new AirportWritableComparable(Integer.parseInt(code), 0),
                    new Text(description)
            );
        }
    }
}
