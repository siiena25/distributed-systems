import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlightWritableComparable implements WritableComparable {

    private int airportId;
    private int code;

    public FlightWritableComparable(int airportId, int code) {
        this.airportId = airportId;
        this.code = code;
    }

    public FlightWritableComparable() { }

    public int getAirportId() {
        return airportId;
    }

    public int getCode() {
        return code;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int compareTo(FlightWritableComparable flight) {
        if (airportId > flight.airportId) {
            return 1;
        } else if (airportId != flight.airportId) {
            return -1;
        }

        if (airportId > flight.code) {
            return 1;
        } else if (airportId != flight.code) {
            return -1;
        }
        return 0;
    }

    public int compareByAirportId(FlightWritableComparable firstItem, FlightWritableComparable secondItem) {
        return Integer.compare(firstItem.getAirportId(), secondItem.getAirportId());
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
