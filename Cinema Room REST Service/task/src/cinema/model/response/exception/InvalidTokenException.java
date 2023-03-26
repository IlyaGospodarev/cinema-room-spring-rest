package cinema.model.response.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Wrong token!");
    }
}
