package cinema.model.response.exeption;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("The password is wrong!");
    }
}
