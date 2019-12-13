package team.priceservercomponents.cinema.model.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import team.priceservercomponents.cinema.model.entity.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>, PagingAndSortingRepository<Ticket, Long> {
    List<Ticket> findBySessionId(long sessionId);
    Optional<Ticket> findById(long id);
}
