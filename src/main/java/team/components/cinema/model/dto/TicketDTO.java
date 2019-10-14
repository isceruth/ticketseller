package team.components.cinema.model.dto;

import team.components.cinema.model.entity.Session;
import team.components.cinema.model.entity.User;
import team.components.cinema.model.enums.CinemaName;

public class TicketDTO {
    private Long seat;

    private User owner;

    private Session session;

    private CinemaName cinema;

    public Long getSeat() {
        return seat;
    }

    public User getOwner() {
        return owner;
    }

    public Session getSession() {
        return session;
    }

    public CinemaName getCinema() {
        return cinema;
    }
}
