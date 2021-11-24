package akka;

public class FunctionResult {
    private String packageId;
    private String title;
    private String result;

    public FunctionResult(String packageId, String title, String result) {
        this.packageId = packageId;
        this.title = title;
        this.result = result;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
