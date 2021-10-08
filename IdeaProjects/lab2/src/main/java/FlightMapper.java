import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, Text, FlightWritableComparable> {
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Mapper<LongWritable, Text, Text, FlightWritableComparable>.Context context)
            throws
            IOException,
            InterruptedException {
        super.map(key, value, context);
    }
}
