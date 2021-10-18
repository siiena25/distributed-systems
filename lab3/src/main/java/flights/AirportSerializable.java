package flights;

import java.io.Serializable;

public class AirportSerializable implements Serializable {

    private int originAirportId;
    private int destAirportId;
    private float delay;
    private boolean isCancelled;

    public AirportSerializable(int originAirportId) {
        this.originAirportId = originAirportId;
    }
}
