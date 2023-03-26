package cinema.service;

import cinema.repository.CinemaRoomRepository;
import cinema.model.Seat;
import cinema.model.response.StatisticsResponse;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final CinemaRoomRepository cinemaRoomRepository;

    public StatisticsService(CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    private int getCurrentIncome() {
        return cinemaRoomRepository.getPurchasedSeats().values().stream().mapToInt(Seat::price).sum();
    }

    private int getNumberOfAvailableSeats() {
        return cinemaRoomRepository.getAvailableSeats().size();
    }

    private int getNumberOfPurchasedTickets() {
        return cinemaRoomRepository.getPurchasedSeats().size();
    }

    public StatisticsResponse getStatistics() {
        return new StatisticsResponse(
                getCurrentIncome(),
                getNumberOfAvailableSeats(),
                getNumberOfPurchasedTickets()
        );
    }
}
