package team.components.cinema.model.service;

import org.springframework.stereotype.Service;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.repository.RemoteMockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RemoteTicketService implements TicketInformation {
    private final RemoteMockRepository remoteMockRepository;

    public RemoteTicketService(RemoteMockRepository remoteMockRepository) {
        this.remoteMockRepository = remoteMockRepository;
    }

    @Override
    public List<Ticket> findAllTickets() {
        return remoteMockRepository.getTickets();
    }

    @Override
    public Optional<Ticket> findTicketById(long id) {
        return Optional.ofNullable(remoteMockRepository.getTicketById((int) id));
    }
}
