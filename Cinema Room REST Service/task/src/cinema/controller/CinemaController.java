package cinema.controller;

import cinema.model.response.StatisticsResponse;
import cinema.model.response.TicketReservationResponse;
import cinema.model.response.TicketRefundResponse;
import cinema.model.request.SeatDto;
import cinema.model.request.Token;
import cinema.model.response.exception.InvalidPasswordException;
import cinema.repository.CinemaRoomRepository;
import cinema.model.Seat;
import cinema.service.StatisticsService;
import cinema.service.CinemaService;
import cinema.service.mapper.SeatMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CinemaController {

    @Value("${secret:}")
    private String secret;
    private final CinemaService cinemaService;
    private final SeatMapper seatMapper;
    private final StatisticsService statisticsService;

    public CinemaController(CinemaService cinemaService, SeatMapper seatMapper, StatisticsService statisticsService) {
        this.cinemaService = cinemaService;
        this.seatMapper = seatMapper;
        this.statisticsService = statisticsService;
    }

    @GetMapping("/seats")
    public CinemaRoomRepository seats() {
        return cinemaService.getSeats();
    }

    @PostMapping("/purchase")
    public TicketReservationResponse purchase(@RequestBody SeatDto seatDto) {
        Seat seat = seatMapper.asSeat(seatDto);
        UUID uuid = cinemaService.purchaseSeat(seatDto);
        return new TicketReservationResponse(uuid, seat);
    }

    @PostMapping("/return")
    public TicketRefundResponse refund(@RequestBody Token token) {
        Seat refundedSeat = cinemaService.refundTicket(token.token());
        return new TicketRefundResponse(refundedSeat);
    }

    @PostMapping("/stats")
    public StatisticsResponse stats(@RequestParam(required = false) String password) {
        if (secret.equals(password)) {
            return statisticsService.getStatistics();
        }
        throw new InvalidPasswordException();
    }
}
