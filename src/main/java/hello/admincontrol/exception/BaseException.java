package hello.admincontrol.exception;


public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    protected int code;
    public int getCode() {
        return this.code;
    }

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
        this.code = 400;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return this.reason;
    }
}

