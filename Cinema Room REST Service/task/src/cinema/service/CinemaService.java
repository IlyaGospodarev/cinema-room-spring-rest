package cinema.service;

import cinema.model.request.SeatDto;
import cinema.model.response.exeption.InvalidTokenException;
import cinema.model.response.exeption.SeatAlreadyPurchasedException;
import cinema.model.response.exeption.SeatNumberOutOfBoundException;
import cinema.repository.CinemaRoomRepository;
import cinema.model.Seat;
import cinema.service.mapper.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CinemaService {
    private final CinemaRoomRepository cinemaRoomRepository;
    private final SeatMapper seatMapper;

    public CinemaService(CinemaRoomRepository cinemaRoomRepository, SeatMapper seatMapper) {
        this.cinemaRoomRepository = cinemaRoomRepository;
        this.seatMapper = seatMapper;
    }

    public CinemaRoomRepository getSeats() {
        return cinemaRoomRepository;
    }

    public UUID purchaseSeat(SeatDto seatDto) {
        Seat seat = seatMapper.asSeat(seatDto);

        boolean isValid = !isSeatValid(seat);
        if (isValid) {
            throw new SeatNumberOutOfBoundException();
        }

        if (!cinemaRoomRepository.isSeatAvailable(seat)) {
            throw new SeatAlreadyPurchasedException();
        }

        UUID uuid = UUID.randomUUID();
        cinemaRoomRepository.getPurchasedSeats().put(uuid, seat);
        cinemaRoomRepository.getAvailableSeats().remove(seat);
        return uuid;
    }

    public Seat refundTicket(UUID uuid) {
        Seat seat = cinemaRoomRepository.getPurchasedSeats().remove(uuid);

        if (seat == null) {
            throw new InvalidTokenException();
        }

        cinemaRoomRepository.getAvailableSeats().add(seat);
        return seat;
    }

    private boolean isSeatValid(Seat seat) {
        return !(seat.row() > cinemaRoomRepository.getTotalRows()
                || seat.column() > cinemaRoomRepository.getTotalColumns()
                || seat.row() < 1
                || seat.column() < 1);
    }
}
