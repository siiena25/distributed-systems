package flights;

import java.io.Serializable;

public class AirportSerializable implements Serializable {

    private int originAirportId;
    private int destAirportId;
    private float delay;
    private boolean isCancelled;

    public AirportSerializable(int originAirportId, int destAirportId, float delay, boolean isCancelled) {
        this.originAirportId = originAirportId;
        this.destAirportId = destAirportId;
        this.delay = delay;
        this.isCancelled = isCancelled;
    }

    public int getOriginAirportId() {
        return originAirportId;
    }

    public void setOriginAirportId(int originAirportId) {
        this.originAirportId = originAirportId;
    }

    public int getDestAirportId() {
        return destAirportId;
    }

    public void setDestAirportId(int destAirportId) {
        this.destAirportId = destAirportId;
    }

    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
