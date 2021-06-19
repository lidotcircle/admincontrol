package hello.admincontrol.exception;


public class Unauthorized extends BaseException {
	private static final long serialVersionUID = 1;

    public Unauthorized(String reason) {
        super(reason);
    }

    public Unauthorized() {
        super("Unauthorized");
        this.code = 401;
    }
}
