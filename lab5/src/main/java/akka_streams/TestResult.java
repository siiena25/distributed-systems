package akka_streams;

public class TestResult {
    private final String url;
    private final int requestsCount;
    private final long requestsTime;
    private final long averageRequestTime = -1;

    public TestResult(String url, int requestsCount, long requestsTime) {
        this.url = url;
        this.requestsCount = requestsCount;
        this.requestsTime = requestsTime;
    }

    public static String getUrl() {
        return url;
    }

    public static int getRequestsCount() {
        return requestsCount;
    }

    public long getRequestsTime() {
        return requestsTime;
    }

    public long getAverageRequestTime() {
        return requestsTime / requestsCount;
    }

    public TestResult add(TestResult testResult) {
        return new TestResult(
                testResult.getUrl(),
                getRequestsCount() + testResult.getRequestsCount(),
                getRequestsTime() + testResult.getRequestsTime());
    }
}
