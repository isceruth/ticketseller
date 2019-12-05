package team.searchcomponents.cinema.model.service;

import org.springframework.data.jpa.domain.Specification;
import team.searchcomponents.cinema.model.entity.Ticket;

import java.util.Optional;

public interface TicketInformation {
    default Iterable<Ticket> findAllTickets() {
        throw new UnsupportedOperationException();
    }

    default Iterable<Ticket> findAllTickets(Specification<Ticket> specs) {
        throw new UnsupportedOperationException("This service does not support filtering");
    }

    Optional<Ticket> findTicketById(long id);
}
