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
        return Integer.compare(code, flight.code);
    }

    public int compareByAirportId(FlightWritableComparable flight) {
        if (this.airportId == flight.airportId && this.code == flight.code) {
            return 0;
        } else {
            return this.airportId - flight.airportId;
        }
        //return Integer.compare(this.getAirportId(), flight.getAirportId());
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
