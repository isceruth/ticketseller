package team.components.graphql.model.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import team.components.graphql.model.Ticket;
import team.components.graphql.model.service.TicketService;

import java.util.List;

@Component
public class TicketQueryResolver implements GraphQLQueryResolver {
    private final TicketService ticketService;

    public TicketQueryResolver(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Ticket getTicketById(final long id) {
        return ticketService.getTicketById(id);
    }

    public List<Ticket> getTickets() {
        return ticketService.getAllTickets();
    }
}
