package akka_streams;

public class TestResultParams {
    private final String url;
    private final int count;

    public TestResultParams(String url, int count) {
        this.url = url;
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public int getCount() {
        return count;
    }
}
