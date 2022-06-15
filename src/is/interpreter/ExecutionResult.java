package is.interpreter;

public class ExecutionResult {

    private boolean status;
    private String message;

    public ExecutionResult(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isExecuted() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
