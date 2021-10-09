import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportsMapper extends Mapper<LongWritable, Text, FlightWritableComparable, Text> {

    private static int AIRPORT_ID = 

    @Override
    protected void map(LongWritable key,
                       Text value,
                       Context context)
            throws IOException, InterruptedException {
        super.map(key, value, context);
        String[] line = value.toString().split(",");
        if (key.get() > 0) {
            String airportId = line[0].replaceAll("\"", "");
            String code = line[1].replaceAll("\"", "");
            context.write(
                    new FlightWritableComparable(Integer.parseInt(airportId), 0),
                    new Text(code)
                    );
        }
    }
}
