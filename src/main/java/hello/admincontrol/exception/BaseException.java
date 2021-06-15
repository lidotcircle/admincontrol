package hello.admincontrol.exception;


public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private String reason;
    public String getReason() {
        return this.reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public BaseException() {
    }

    public BaseException(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return this.reason;
    }
}

