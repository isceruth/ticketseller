package team.components.cinema.model.entity;

import team.components.cinema.model.dto.TicketDTO;
import team.components.cinema.model.enums.CinemaName;

public class TicketFactory {
    public static Ticket getTicket(CinemaName name, TicketDTO ticket) {
        if (name == CinemaName.ALPHA) {
            return new Ticket.Builder()
                    .setOwner(ticket.getOwner())
                    .setSeat(ticket.getSeat())
                    .setPrice(100)
                    .setSession(ticket.getSession())
                    .build();
        } else if (name == CinemaName.OMEGA) {
            return new Ticket.Builder()
                    .setOwner(ticket.getOwner())
                    .setSeat(ticket.getSeat())
                    .setPrice(200)
                    .setSession(ticket.getSession())
                    .build();
        } else {
            throw new IllegalArgumentException("Wrong cinema name:" + name);
        }
    }
}