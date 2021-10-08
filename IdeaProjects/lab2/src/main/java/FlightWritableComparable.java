import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlightWritableComparable implements WritableComparable<FlightWritableComparable> {

    private int airportId;
    private int code;

    public FlightWritableComparable(int airportId, int code) {
        this.airportId = airportId;
        this.code = code;
    }

    @Override
    public int compareTo(FlightWritableComparable o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

    }
}
