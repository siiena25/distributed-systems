package akka;

import java.util.ArrayList;

public class MessageStore {
    private final Integer packageId;
    private final ArrayList<Test> tests;

    public MessageStore(Integer packageId, ArrayList<Test> tests) {
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
