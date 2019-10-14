package team.components.cinema.model.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team.components.cinema.model.dto.TicketDTO;
import team.components.cinema.model.entity.TicketAlpha;
import team.components.cinema.model.entity.User;
import team.components.cinema.model.repository.TicketRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    public Iterable<TicketAlpha> findAllTickets(Specification<TicketAlpha> specs) {
        return ticketRepository.findAll(specs);
    }

    public Optional<TicketAlpha> findTicketById(long id) {
        return ticketRepository.findById(id);
    }

    public void deleteTicketById(long id) {
        ticketRepository.deleteById(id);
    }

    public TicketAlpha createTicket(TicketDTO ticket) {
        TicketAlpha newTicket = new TicketAlpha.Builder()
                .setOwner(ticket.getOwner())
                .setPrice(ticket.getPrice())
                .setSeat(ticket.getSeat())
                .setSession(ticket.getSession())
                .build();

        return ticketRepository.save(newTicket);
    }

    public void changeOwner(long ticketId, long ownerId) {
        User user = userService.getUserById(ownerId);
        Optional<TicketAlpha> ticket = findTicketById(ticketId);

        if (!ticket.isPresent()) {
            throw new NoSuchElementException();
        }

        TicketAlpha ticketToUpdate = ticket.get();
        ticketToUpdate.setOwner(user);
        ticketRepository.save(ticketToUpdate);
    }
}
