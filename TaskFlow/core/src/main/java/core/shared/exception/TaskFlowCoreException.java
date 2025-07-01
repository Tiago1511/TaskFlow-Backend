package core.shared.exception;

public class TaskFlowCoreException extends RuntimeException {
  public int error;
  public String detailMessage;

  public TaskFlowCoreException(String message, int error) {
        super(message);
        this.detailMessage = message;
        this.error = error;
  }

  public String getDetailMessage() {
        return detailMessage;
  }

}
