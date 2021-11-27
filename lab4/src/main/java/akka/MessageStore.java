package akka;

import java.util.ArrayList;

public class FunctionResult {
    private final String packageId;
    private final ArrayList<Test> tests;

    public FunctionResult(String packageId, ArrayList<Test> tests) {
        this.packageId = packageId;
        this.tests = tests;
    }

    public String getPackageId() {
        return packageId;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
