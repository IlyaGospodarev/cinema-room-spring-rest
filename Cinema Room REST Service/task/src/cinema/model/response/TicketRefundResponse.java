package cinema.model.response;

import cinema.model.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TicketRefundResponse(@JsonProperty("returned_ticket") Seat returnedTicket) {
}
