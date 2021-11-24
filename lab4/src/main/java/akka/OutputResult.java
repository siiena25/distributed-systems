package akka;

import java.util.Map;

public class OutputResult {
    private String id;
    private Map<String, String> result;

    public OutputResult(String id, Map<String, String> result) {
        this.id = id;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}
