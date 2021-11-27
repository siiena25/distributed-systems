package akka;

public class MessageObject {
    private final String packageId;

    public MessageObject(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }
}
