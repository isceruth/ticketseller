package team.components.cinema.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import team.components.cinema.model.entity.Session;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<Session, Long> {
}
