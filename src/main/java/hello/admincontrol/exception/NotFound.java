package hello.admincontrol.exception;


public class NotFound extends BaseException {
	private static final long serialVersionUID = 1;

    public NotFound(String reason) {
        super(reason);
    }

    public NotFound() {
        super("NotFound");
    }
}
