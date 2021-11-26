package akka;

public class FunctionResult {
    private final String packageId;
    private final String testName;
    private final String result;

    public FunctionResult(String packageId, String title, String result) {
        this.packageId = packageId;
        this.testName = title;
        this.result = result;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getTestName() {
        return testName;
    }

    public String getResult() {
        return result;
    }
}
