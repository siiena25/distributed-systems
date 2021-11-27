package akka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ResultStoreFunction {
    private final String packageId;
    private final String script;
    private final String functionTitle;
    private final Test test;

    @JsonCreator
    public ResultStoreFunction(
            @JsonProperty("packageId") String packageId,
            @JsonProperty("jsScript") String script,
            @JsonProperty("functionName") String functionTitle,
            @JsonProperty("tests") Test test
    ) {
        this.packageId = packageId;
        this.script = script;
        this.functionTitle = functionTitle;
        this.test = test;
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

    public Test getTest() {
        return test;
    }
}
