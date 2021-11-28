package akka.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {
    @JsonProperty("testName")
    private final String testName;

    @JsonProperty("expectedResult")
    private final String expectedResult;

    @JsonProperty("params")
    private final ArrayList<Integer> params;

    @JsonProperty("result")
    private boolean result;

    @JsonCreator
    public Test(
            @JsonProperty("testName") String testName,
            @JsonProperty("expectedResult") String expectedResult,
            @JsonProperty("params") ArrayList<Integer> params
    ) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
        this.result = false;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
