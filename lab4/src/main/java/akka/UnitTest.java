package akka;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;

public class UnitTest {
    private final String packageId;
    private final String script;
    private final String testTitle;
    private final String functionTitle;
    private final String result;
    private final ArrayList<Integer> params;

    public UnitTest(String packageId, String script, String testTitle, String functionTitle, String result, ArrayList<Integer> params) {
        this.packageId = packageId;
        this.script = script;
        this.testTitle = testTitle;
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
        return testTitle;
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
}
