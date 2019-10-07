package team.components.cinema.model.specification;

import org.springframework.data.jpa.domain.Specification;
import team.components.cinema.model.entity.Ticket;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TicketIsNotClaimed implements Specification<Ticket> {
    private Boolean required;

    public TicketIsNotClaimed(Boolean required) {
        this.required = required;
    }

    @Override
    public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (required == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return required
                ? criteriaBuilder.isNotNull(root.get("owner"))
                : criteriaBuilder.isNull(root.get("owner"));
    }
}
