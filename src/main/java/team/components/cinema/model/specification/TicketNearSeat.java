package team.components.cinema.model.specification;

import org.springframework.data.jpa.domain.Specification;
import team.components.cinema.model.entity.Ticket;

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
        if (seat == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.between(root.get("seat"), seat - 2, seat + 2);
    }
}
