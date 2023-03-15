package cinema.dto;

import cinema.model.Seat;

import java.util.UUID;

public class PurchasedTicket {
    UUID token;
    Seat ticket;

    public PurchasedTicket(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
