package flights;

import java.io.Serializable;

public class FlightsSerializable implements Serializable {

    private float maxTimeOfDelay;
    private float delayFlights;
    private float cancelledFlights;
    private int numberOfFlights;

    public FlightsSerializable() {
    }

    public FlightsSerializable(float maxTimeOfDelay, int delayFlights, int cancelledFlights, int numberOfFlights) {
        this.maxTimeOfDelay = maxTimeOfDelay;
        this.delayFlights = delayFlights;
        this.cancelledFlights = cancelledFlights;
        this.numberOfFlights = numberOfFlights;
    }

    public float getMaxTimeOfDelay() {
        return maxTimeOfDelay;
    }

    public void setMaxTimeOfDelay(float maxTimeOfDelay) {
        this.maxTimeOfDelay = maxTimeOfDelay;
    }

    public int getDelayFlights() {
        return delayFlights;
    }

    public void setDelayFlights(int delayFlights) {
        this.delayFlights = delayFlights;
    }

    public int getCancelledFlights() {
        return cancelledFlights;
    }

    public void setCancelledFlights(int cancelledFlights) {
        this.cancelledFlights = cancelledFlights;
    }

    public int getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setNumberOfFlights(int numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    public static FlightsSerializable plus(FlightsSerializable firstFlight, FlightsSerializable secondFlight) {
        return new FlightsSerializable(
                firstFlight.getMaxTimeOfDelay() + secondFlight.getMaxTimeOfDelay(),
                firstFlight.getDelayFlights() + secondFlight.getDelayFlights(),
                firstFlight.getCancelledFlights() + secondFlight.getCancelledFlights(),
                firstFlight.getNumberOfFlights() + secondFlight.getNumberOfFlights()
        );
    }

    public static FlightsSerializable add(FlightsSerializable flight, AirportSerializable airport) {
        boolean isDelayed = airport.getDelay() > 0.f || airport.isCancelled();
        float delayFlight = Math.max(flight.getMaxTimeOfDelay(), airport.getDelay());
        float isDelayedFlight = isDelayed ? flight.getDelayFlights() + 1 : flight.getDelayFlights();
        float isCancelledFlight = airport.isCancelled() ? flight.getCancelledFlights() + 1 : flight.getCancelledFlights();
        return new FlightsSerializable(
                delay,

        );
    }
}
