package cinema.model.response.exeption;

public class SeatNumberOutOfBoundException extends RuntimeException {
    public SeatNumberOutOfBoundException() {
        super("The number of a row or a column is out of bounds!");
    }
}
