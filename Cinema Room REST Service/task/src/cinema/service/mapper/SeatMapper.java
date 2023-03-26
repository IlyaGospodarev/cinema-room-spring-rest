package cinema.service.mapper;

import cinema.model.Seat;
import cinema.model.request.SeatDto;
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
        int row = dto.row();
        int column = dto.column();
        int price = row < priceSwitchRow ? highPrice : lowPrice;
        return new Seat(row, column, price);
    }

    public Seat creatSeat(int row, int column){
        int price = row < priceSwitchRow ? highPrice : lowPrice;
        return new Seat(row, column, price);
    }
}
