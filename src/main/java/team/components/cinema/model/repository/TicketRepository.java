package team.components.cinema.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import team.components.cinema.model.entity.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaSpecificationExecutor<Ticket>, PagingAndSortingRepository<Ticket, Long> {
    List<Ticket> findBySessionId(long sessionId);
    Optional<Ticket> findById(long id);
    Page<Ticket> findAll(Pageable pageable);
}
