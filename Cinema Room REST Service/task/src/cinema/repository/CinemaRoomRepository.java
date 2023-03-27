package cinema.repository;

import cinema.model.Seat;
import cinema.service.mapper.SeatMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class CinemaRoomRepository {
    @Value("${cinema-rows}")
    @JsonProperty("total_rows")
    private int totalRows;
    @Value("${cinema-columns}")
    @JsonProperty("total_columns")
    private int totalColumns;
    @JsonProperty("available_seats")
    private final List<Seat> availableSeats;
    @JsonIgnore
    private final Map<UUID, Seat> purchasedSeats;
    @JsonIgnore
    private final SeatMapper seatMapper;

    @Autowired
    public CinemaRoomRepository(SeatMapper seatMapper) {
        this.availableSeats = new ArrayList<>();
        this.purchasedSeats = new HashMap<>();
        this.seatMapper = seatMapper;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public Map<UUID, Seat> getPurchasedSeats() {
        return purchasedSeats;
    }

    public boolean isSeatAvailable(Seat seat) {
        return availableSeats.contains(seat);
    }

    @PostConstruct
    private void init() {
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalRows; column++) {
                Seat seat = seatMapper.creatSeat(row, column);
                availableSeats.add(seat);
            }
        }
    }
}
