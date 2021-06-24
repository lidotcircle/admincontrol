package hello.admincontrol.exception;


public class BadRequest extends BaseException {
	private static final long serialVersionUID = 1;

	public BadRequest(String reason) {
        super(reason);
    }

    public BadRequest() {
        super("BadRequest");
        this.code = 400;
    }
}

