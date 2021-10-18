package flights;

import java.io.Serializable;

public class FlightsSerializable implements Serializable {

    private float maxTimeOfDelay;
    private float delayFlights;
    private float cancelledFlights;
    private int numberOfFlights;

    public FlightsSerializable() {
    }

    public FlightsSerializable(float maxTimeOfDelay, float delayFlights, float cancelledFlights, int numberOfFlights) {
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

    public float getDelayFlights() {
        return delayFlights;
    }

    public void setDelayFlights(int delayFlights) {
        this.delayFlights = delayFlights;
    }

    public float getCancelledFlights() {
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

    public static FlightsSerializable merge(FlightsSerializable firstFlight, FlightsSerializable secondFlight) {
        return new FlightsSerializable(
                firstFlight.getMaxTimeOfDelay() + secondFlight.getMaxTimeOfDelay(),
                firstFlight.getDelayFlights() + secondFlight.getDelayFlights(),
                firstFlight.getCancelledFlights() + secondFlight.getCancelledFlights(),
                firstFlight.getNumberOfFlights() + secondFlight.getNumberOfFlights()
        );
    }

    public static FlightsSerializable mergeValue(FlightsSerializable flight, AirportSerializable airport) {
        float delayFlight = Math.max(flight.getMaxTimeOfDelay(), airport.getDelay());
        int numberOfFlights = flight.getNumberOfFlights() + 1;
        float isDelayedFlight = (airport.getDelay() > 0.f || airport.isCancelled()) ?
                flight.getDelayFlights() + 1 : flight.getDelayFlights();
        float isCancelledFlight = airport.isCancelled() ?
                flight.getCancelledFlights() + 1 : flight.getCancelledFlights();

        return new FlightsSerializable(delayFlight, isDelayedFlight, isCancelledFlight, numberOfFlights);
    }
}
