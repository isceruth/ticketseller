package team.components.cinema.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.components.cinema.model.entity.Cinema;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Long> {
}
