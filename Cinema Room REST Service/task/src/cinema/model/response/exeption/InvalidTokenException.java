package cinema.model.response.exeption;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Wrong token!");
    }
}
