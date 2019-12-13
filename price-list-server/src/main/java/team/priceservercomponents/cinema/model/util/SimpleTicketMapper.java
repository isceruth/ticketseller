package team.priceservercomponents.cinema.model.util;

import team.priceservercomponents.cinema.model.dto.SimpleTicket;
import team.priceservercomponents.cinema.model.entity.Ticket;

public class SimpleTicketMapper {
    public static SimpleTicket toSimpleTicket(Ticket ticket) {
        return new SimpleTicket(ticket.getPrice(), ticket.getSeat());
    }
}
