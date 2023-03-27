package cinema.service;

import cinema.repository.CinemaRoomRepository;
import cinema.model.Seat;
import cinema.model.response.StatisticsResponse;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    private final CinemaRoomRepository cinemaRoomRepository;

    public StatisticService(CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    public int getCurrentIncome() {
        return cinemaRoomRepository.getPurchasedSeats()
                .values()
                .stream()
                .mapToInt(Seat::price)
                .sum();
    }


    public int getNumberOfAvailableSeats() {
        return cinemaRoomRepository.getAvailableSeats()
                .size();
    }


    public int getNumberOfPurchasedTickets() {
        return cinemaRoomRepository.getPurchasedSeats()
                .size();
    }

    public StatisticsResponse getStatistics() {
        return new StatisticsResponse(
                getCurrentIncome(),
                getNumberOfAvailableSeats(),
                getNumberOfPurchasedTickets());
    }
}
