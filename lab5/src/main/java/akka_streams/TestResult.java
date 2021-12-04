package akka_streams;

public class TestResult {
    private final String url;
    private final int requestsCount;
    private final int requestsTime;

    public TestResult(String url, int requestsCount, int requestsTime) {
        this.url = url;
        this.requestsCount = requestsCount;
        this.requestsTime = requestsTime;
    }
}
