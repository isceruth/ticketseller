package team.components.cinema.model.dto;

public class SimpleTicket {
    private Long price;
    private Long seat;

    public SimpleTicket(Long price, Long seat) {
        this.price = price;
        this.seat = seat;
    }

    public Long getPrice() {
        return price;
    }

    public Long getSeat() {
        return seat;
    }
}
