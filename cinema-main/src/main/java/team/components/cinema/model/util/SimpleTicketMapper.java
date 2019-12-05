package team.components.cinema.model.util;

import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.dto.SimpleTicket;

public class SimpleTicketMapper {
    public static SimpleTicket toSimpleTicket(Ticket ticket) {
        return new SimpleTicket(ticket.getPrice(), ticket.getSeat());
    }
}
