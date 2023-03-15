package cinema.dto;

public class SeatDto {
    int row;
    int column;

    public SeatDto() {}

    public SeatDto(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
