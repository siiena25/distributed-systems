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
            
        }
        super.map(key, value, context);
    }
}
