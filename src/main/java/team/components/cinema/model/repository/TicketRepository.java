package team.components.cinema.model.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.components.cinema.model.entity.TicketAlpha;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<TicketAlpha, Long>, JpaSpecificationExecutor<TicketAlpha> {
    List<TicketAlpha> findBySessionId(long sessionId);
    Optional<TicketAlpha> findById(long id);
}
