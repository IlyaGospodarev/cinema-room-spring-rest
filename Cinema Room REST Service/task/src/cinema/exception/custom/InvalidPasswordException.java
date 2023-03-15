package cinema.exception.custom;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("The password is wrong!");
    }
}
