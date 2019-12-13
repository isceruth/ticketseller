package team.components.cinema.model.service;

import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team.components.cinema.model.dto.TicketDTO;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.entity.User;
import team.components.cinema.model.repository.TicketRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TicketService implements TicketInformation {
    private final TicketRepository ticketRepository;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    @Override
    @Cacheable(value = "tickets")
    public Iterable<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    @Cacheable(value = "tickets", key = "#params.toString()")
    public Iterable<Ticket> findAllTickets(Specification<Ticket> specs, JSONObject params) {
        return ticketRepository.findAll(specs);
    }

    @Override
    public Optional<Ticket> findTicketById(long id) {
        return ticketRepository.findById(id);
    }

    public void deleteTicketById(long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket createTicket(TicketDTO ticket) {
        Ticket newTicket = new Ticket.Builder()
                .setOwner(ticket.getOwner())
                .setPrice(ticket.getPrice())
                .setSeat(ticket.getSeat())
                .setSession(ticket.getSession())
                .build();

        return ticketRepository.save(newTicket);
    }

    public void changeOwner(long ticketId, long ownerId) {
        User user = userService.getUserById(ownerId);
        Optional<Ticket> ticket = findTicketById(ticketId);

        if (!ticket.isPresent()) {
            throw new NoSuchElementException();
        }

        Ticket ticketToUpdate = ticket.get();
        ticketToUpdate.setOwner(user);
        ticketRepository.save(ticketToUpdate);
    }
}
