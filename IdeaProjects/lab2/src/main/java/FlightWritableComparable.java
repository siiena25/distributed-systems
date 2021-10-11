import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlightWritableComparable implements WritableComparable<FlightWritableComparable> {

    private int airportId;
    private int code;

    FlightWritableComparable() {}

    FlightWritableComparable(int airportId, int code) {
        this.airportId = airportId;
        this.code = code;
    }

    public int getAirportId() {
        return airportId;
    }

    @Override
    public int compareTo(FlightWritableComparable flight) {
        if (airportId == flight.airportId && code == flight.code) {
            return 0;
        } else {
            return -1;
        }
    }

    public int compareByAirportId(FlightWritableComparable item) {
        //return Integer.compare(this.getAirportId(), item.getAirportId());
        if (this.getAirportId() > item.airportId) {
            return 1;
        } else if (this.getAirportId() != item.airportId) {
            return -1;
        } else return 0;
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
