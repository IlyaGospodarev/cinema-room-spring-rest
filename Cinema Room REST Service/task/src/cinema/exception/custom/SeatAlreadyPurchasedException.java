package cinema.exception.custom;

public class SeatAlreadyPurchasedException extends RuntimeException{
    public SeatAlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }
}
