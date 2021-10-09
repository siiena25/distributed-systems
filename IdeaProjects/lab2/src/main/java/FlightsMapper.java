import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightsMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final int AIRPORT_ID = 14;
    private static final int AIRPORT_DELAY = 18;

    @Override
    protected void map(
            LongWritable key,
            Text value,
            Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        super.map(key, value, context);
        String[] line = value.toString().split(",");
        if (key.get() > 0) {
            float delay;
            try {
                delay = Float.parseFloat(line[18]);
            } catch (NumberFormatException e) {
                delay = 0;
            }
            if (delay > 0) {
                
            }
        }
    }
}
