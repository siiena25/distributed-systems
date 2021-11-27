package akka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class MessageTests {
    private final String packageId;
    private final String script;
    private final String functionTitle;
    private final ArrayList<Test> tests;

    @JsonCreator
    public MessageTests(
            @JsonProperty("packageId") String packageId,
            @JsonProperty("jsScript") String script,
            @JsonProperty("functionName") String functionTitle,
            @JsonProperty("tests") ArrayList<Test> tests
    ) {
        this.packageId = packageId;
        this.script = script;
        this.functionTitle = functionTitle;
        this.tests = tests;
    }

    public String getPackageId() {
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
