package team.components.cinema.model.service;

import org.json.JSONObject;
import org.springframework.data.jpa.domain.Specification;
import team.components.cinema.model.entity.Ticket;

import java.util.Optional;

public interface TicketInformation {
    default Iterable<Ticket> findAllTickets() {
        throw new UnsupportedOperationException();
    }

    default Iterable<Ticket> findAllTickets(Specification<Ticket> specs, JSONObject params) {
        throw new UnsupportedOperationException("This service does not support filtering");
    }

    Optional<Ticket> findTicketById(long id);
}
