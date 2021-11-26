package akka;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UnitTest {
    private final String packageId;
    private final String script;
    private final String testName;
    private final String functionTitle;
    private final String result;
    private final ArrayList<Integer> params;

    public UnitTest(String packageId, String script, String testName, String functionTitle, String result, ArrayList<Integer> params) {
        this.packageId = packageId;
        this.script = script;
        this.testName = testName;
        this.functionTitle = functionTitle;
        this.result = result;
        this.params = params;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getScript() {
        return script;
    }

    public String getTestTitle() {
        return testName;
    }

    public String getFunctionTitle() {
        return functionTitle;
    }

    public String getResult() {
        return result;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }

    public static ArrayList<UnitTest> funcHandler(ResultStoreFunction function) {
        return function.getTests().stream().map(test -> new UnitTest(
                function.getPackageId(),
                function.getScript(),
                function.getFunctionTitle(),
                test.getTestName(),
                test.getExpectedResult(),
                test.getParams()
        )).collect(Collectors.toCollection(ArrayList::new));
    }
}
