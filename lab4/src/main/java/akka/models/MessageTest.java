package akka.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageTest {
    private final Integer packageId;
    private final String script;
    private final String functionTitle;
    private final Test test;

    @JsonCreator
    public MessageTest(
            @JsonProperty("packageId") Integer packageId,
            @JsonProperty("jsScript") String script,
            @JsonProperty("functionName") String functionTitle,
            @JsonProperty("tests") Test test
    ) {
        this.packageId = packageId;
        this.script = script;
        this.functionTitle = functionTitle;
        this.test = test;
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

    public Test getTest() {
        return test;
    }
}
