package team.components.cinema.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import team.components.cinema.model.Session;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<Session, Long> {
}
