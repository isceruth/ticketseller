package team.components.graphql.model.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import team.components.graphql.model.TicketDeleteInput;
import team.components.graphql.model.service.TicketService;

@Component
public class TicketMutationResolver implements GraphQLMutationResolver {
    private final TicketService ticketService;

    public TicketMutationResolver(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    void deleteTicket(TicketDeleteInput input) {
        ticketService.deleteTicketById(input.getId());
    }
}
