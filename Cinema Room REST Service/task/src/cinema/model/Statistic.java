package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Statistic {

    private final CinemaRoom cinemaRoom;

    public Statistic(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }
    @JsonProperty("current_income")
    public int getCurrentIncome() {
        return cinemaRoom.getPurchasedSeats().values().stream().mapToInt(Seat::getPrice).sum();
    }
    @JsonProperty("number_of_available_seats")
    public int getNumberOfAvailableSeats() {
        return cinemaRoom.getAvailableSeats().size();
    }
    @JsonProperty("number_of_purchased_tickets")
    public int getNumberOfPurchasedTickets() {
        return cinemaRoom.getPurchasedSeats().size();
    }
}
