import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportWritableComparable implements WritableComparable<AirportWritableComparable> {

    private int airportId;
    private int code;

    AirportWritableComparable() {}

    AirportWritableComparable(int airportId, int code) {
        this.airportId = airportId;
        this.code = code;
    }

    public int getAirportId() {
        return airportId;
    }

    @Override
    public int compareTo(AirportWritableComparable flight) {
        if (airportId > flight.airportId) {
            return 1;
        } else if (airportId < flight.airportId) {
            return -1;
        }

        if (code > flight.code) {
            return 1;
        } else if (code < flight.code) {
            return -1;
        }

        return 0;
    }

    public int compareByAirportId(AirportWritableComparable flight) {
        if (airportId > flight.airportId) {
            return 1;
        } else if (airportId < flight.airportId) {
            return -1;
        }
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
