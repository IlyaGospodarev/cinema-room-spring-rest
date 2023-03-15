package cinema.util;

import cinema.model.Seat;
import cinema.dto.SeatDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {
    @Value("${low-price}")
    private int lowPrice;
    @Value("${high-price}")
    private int highPrice;
    @Value("${price-switch-row}")
    private int priceSwitchRow;

    public Seat asSeat(SeatDto dto) {
        int row = dto.getRow();
        int column = dto.getColumn();
        int price = row < priceSwitchRow ? highPrice : lowPrice;
        return new Seat(row, column, price);
    }

    public Seat creatSeat(int row, int column){
        int price = row < priceSwitchRow ? highPrice : lowPrice;
        return new Seat(row, column, price);
    }
}
