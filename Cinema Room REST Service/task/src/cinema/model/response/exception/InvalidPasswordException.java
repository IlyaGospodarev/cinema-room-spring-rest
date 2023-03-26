package cinema.model.response.exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("The password is wrong!");
    }
}
