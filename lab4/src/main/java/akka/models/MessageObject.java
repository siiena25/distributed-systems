package akka.models;

public class MessageObject {
    private final Integer packageId;

    public MessageObject(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getPackageId() {
        return packageId;
    }
}
