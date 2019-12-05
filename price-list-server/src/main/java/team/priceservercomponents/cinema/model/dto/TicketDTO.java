package team.priceservercomponents.cinema.model.dto;

import team.priceservercomponents.cinema.model.entity.Session;
import team.priceservercomponents.cinema.model.entity.User;
import team.searchcomponents.cinema.model.entity.Session;
import team.searchcomponents.cinema.model.entity.User;

public class TicketDTO {
    private Long seat;

    private Long price;

    private User owner;

    private Session session;

    public Long getSeat() {
        return seat;
    }

    public Long getPrice() {
        return price;
    }

    public User getOwner() {
        return owner;
    }

    public Session getSession() {
        return session;
    }
}
