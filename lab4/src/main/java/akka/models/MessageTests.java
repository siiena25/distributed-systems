package akka.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class MessageTests {
    private final Integer packageId;
    private final String script;
    private final String functionTitle;
    private final ArrayList<Test> tests;

    @JsonCreator
    public MessageTests(
            @JsonProperty("packageId") int packageId,
            @JsonProperty("jsScript") String script,
            @JsonProperty("functionName") String functionTitle,
            @JsonProperty("tests") ArrayList<Test> tests
    ) {
        this.packageId = packageId;
        this.script = script;
        this.functionTitle = functionTitle;
        this.tests = tests;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public String getScript() {
        return script;
    }

    public String getFunctionTitle() {
        return functionTitle;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
