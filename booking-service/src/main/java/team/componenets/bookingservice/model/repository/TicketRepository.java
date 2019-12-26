package team.componenets.bookingservice.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.componenets.bookingservice.model.entity.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
