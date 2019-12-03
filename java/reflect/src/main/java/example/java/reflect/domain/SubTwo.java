package example.java.reflect.domain;

public class SubTwo extends Base {

    @Override
    public String doWork() {
        return "This is sub method two!";
    }

    public String getFirstMethod(String methodParam) {
        return "This is sub first method : " + methodParam;
    }

    public String getSecondMethod(String methodParam) {
        return "This is sub second method : " + methodParam;
    }
}
