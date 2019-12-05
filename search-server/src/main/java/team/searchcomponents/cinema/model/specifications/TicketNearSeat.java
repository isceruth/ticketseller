package team.searchcomponents.cinema.model.specifications;

import org.springframework.data.jpa.domain.Specification;
import team.searchcomponents.cinema.model.entity.Ticket;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TicketNearSeat implements Specification<Ticket> {
    private Long seat;

    public TicketNearSeat(Long seat) {
        this.seat = seat;
    }

    @Override
    public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return seat == null
                ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                : criteriaBuilder.between(root.get("seat"), seat - 1, seat + 1);
    }
}