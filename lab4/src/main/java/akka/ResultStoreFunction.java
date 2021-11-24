package akka;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.Test;

import java.util.ArrayList;

public class ResultStoreFunction {
    private final String packageId;
    private final String script;
    private final String functionTitle;
    private final ArrayList<Test> tests;

    public ResultStoreFunction(
            @JsonProperty("packageId") String packageId,
            @JsonProperty("script") String script,
            @JsonProperty("functionTitle") String functionTitle,
            @JsonProperty("tests") ArrayList<Test> tests
    )
}
