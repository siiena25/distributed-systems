package akka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class MessageStore {
    @JsonProperty("packageId")
    private final Integer packageId;

    @JsonProperty("test")
    private final ArrayList<Test> tests;

    @JsonCreator
    public MessageStore(
            @JsonProperty("packageId") Integer packageId,
            @JsonProperty("test") ArrayList<Test> tests
    ) {
        this.packageId = packageId;
        this.tests = tests;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
