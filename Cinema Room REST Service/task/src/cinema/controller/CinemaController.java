package cinema.controller;

import cinema.dto.PurchasedTicket;
import cinema.dto.ReturnedTicket;
import cinema.dto.SeatDto;
import cinema.dto.Token;
import cinema.exception.custom.InvalidPasswordException;
import cinema.model.CinemaRoom;
import cinema.model.Seat;
import cinema.model.Statistic;
import cinema.service.CinemaService;
import cinema.util.SeatMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CinemaController {
    @Value("${secret}")
    private String secret;
    private final CinemaService cinemaService;
    private final SeatMapper seatMapper;
    private final Statistic statistic;

    public CinemaController(CinemaService cinemaService, SeatMapper seatMapper, Statistic statistic) {
        this.cinemaService = cinemaService;
        this.seatMapper = seatMapper;
        this.statistic = statistic;
    }

    @GetMapping("/seats")
    public CinemaRoom seats() {
        return cinemaService.getSeats();
    }

    @PostMapping("/purchase")
    public PurchasedTicket purchase(@RequestBody SeatDto seatDto) {
        Seat seat = seatMapper.asSeat(seatDto);
        UUID uuid = cinemaService.purchaseSeat(seatDto);
        return new PurchasedTicket(uuid, seat);
    }

    @PostMapping("/return")
    public ReturnedTicket returned(@RequestBody Token token) {
        Seat seat = cinemaService.refundTicket(token.getToken());
        return new ReturnedTicket(seat);
    }

    @PostMapping("/stats")
    public Statistic stats(@RequestParam(required = false) String password) {
        if (password != null && password.equals(secret)) {
            return statistic;
        }
        throw new InvalidPasswordException();
    }
}
