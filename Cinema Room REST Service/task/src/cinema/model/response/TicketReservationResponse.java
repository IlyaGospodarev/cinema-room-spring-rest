package cinema.model.response;

import cinema.model.Seat;

import java.util.UUID;

public record TicketReservationResponse(UUID token, Seat ticket) {
}
