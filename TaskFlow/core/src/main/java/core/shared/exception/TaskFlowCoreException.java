package core.shared.exception;

public class TaskFlowCoreException extends RuntimeException {
  public int error;

  public TaskFlowCoreException(String message, int error) {
        super(message);
        this.error = error;
    }

}
