import com.sun.istack.internal.NotNull;
import org.apache.hadoop.io.WritableComparable;

import javax.annotation.Nullable;
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
    public int compareTo(@Nullable FlightWritableComparable flight) {
        if (airportId > flight.airportId) {
            return 1;
        } else if ()
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(airportId);
        dataOutput.writeInt(code);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        airportId = dataInput.readInt();
        code = dataInput.readInt();
    }
}
