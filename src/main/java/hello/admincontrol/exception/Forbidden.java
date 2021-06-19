package hello.admincontrol.exception;


public class Forbidden extends BaseException {
	private static final long serialVersionUID = 1;

	public Forbidden(String reason) {
        super(reason);
    }

    public Forbidden() {
        super("Forbidden");
        this.code = 403;
    }
}

