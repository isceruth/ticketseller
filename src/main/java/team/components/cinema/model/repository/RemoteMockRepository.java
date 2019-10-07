package team.components.cinema.model.repository;

import org.springframework.stereotype.Component;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.util.InformationGenerator;

import java.util.ArrayList;
import java.util.List;

@Component
public class RemoteMockRepository {
    private final List<Ticket> tickets = new ArrayList<>();
    private final InformationGenerator informationGenerator;

    public RemoteMockRepository(InformationGenerator informationGenerator) {
        this.informationGenerator = informationGenerator;
        informationGenerator.generateTickets(tickets, 15);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Ticket getTicketById(int id) {
        return tickets.get(id);
    }
}
