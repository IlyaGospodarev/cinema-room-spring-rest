package cinema.service;

import cinema.dto.SeatDto;
import cinema.exception.custom.InvalidTokenException;
import cinema.exception.custom.SeatAlreadyPurchasedException;
import cinema.exception.custom.SeatNumberOutOfBoundException;
import cinema.model.CinemaRoom;
import cinema.model.Seat;
import cinema.util.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CinemaService {
    private final CinemaRoom cinemaRoom;
    private final SeatMapper seatMapper;

    public CinemaService(CinemaRoom cinemaRoom, SeatMapper seatMapper) {
        this.cinemaRoom = cinemaRoom;
        this.seatMapper = seatMapper;
    }

    public CinemaRoom getSeats() {
        return cinemaRoom;
    }

    public UUID purchaseSeat(SeatDto seatDto) {
        Seat seat = seatMapper.asSeat(seatDto);

        boolean isValid = !isSeatValid(seat);
        if (isValid) {
            throw new SeatNumberOutOfBoundException();
        }

        if (!cinemaRoom.isSeatAvailable(seat)) {
            throw new SeatAlreadyPurchasedException();
        }

        UUID uuid = UUID.randomUUID();
        cinemaRoom.getPurchasedSeats().put(uuid, seat);
        cinemaRoom.getAvailableSeats().remove(seat);
        return uuid;
    }

    public Seat refundTicket(UUID uuid) {
        Seat seat = cinemaRoom.getPurchasedSeats().remove(uuid);

        if (seat == null) {
            throw new InvalidTokenException();
        }

        cinemaRoom.getAvailableSeats().add(seat);
        return seat;
    }

    private boolean isSeatValid(Seat seat) {
        return !(seat.getRow() > cinemaRoom.getTotalRows()
                || seat.getColumn() > cinemaRoom.getTotalColumns()
                || seat.getRow() < 1
                || seat.getColumn() < 1);
    }
}
