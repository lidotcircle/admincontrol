package hello.admincontrol.exception;


/**
 * 本项目的异常处理基类, 所有业务异常都通过需要继承这个类才能将错误信息传达给用户.
 * 异常类都写在这个目录下.
 */
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

