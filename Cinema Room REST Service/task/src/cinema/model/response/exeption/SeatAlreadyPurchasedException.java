package cinema.model.response.exeption;

public class SeatAlreadyPurchasedException extends RuntimeException{
    public SeatAlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }
}
