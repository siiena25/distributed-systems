package akka_streams;

public class TestResult {
    private final String url;
    private final int requestsCount;
    private final int requestsTime;
    private final float averageRequestTime = -1;

    public TestResult(String url, int requestsCount, int requestsTime) {
        this.url = url;
        this.requestsCount = requestsCount;
        this.requestsTime = requestsTime;
    }

    public String getUrl() {
        return url;
    }

    public int getRequestsCount() {
        return requestsCount;
    }

    public int getRequestsTime() {
        return requestsTime;
    }

    public float getAverageRequestTime() {
        return Float.parseFloat(Integer.toString(requestsTime / requestsCount));
    }
}
