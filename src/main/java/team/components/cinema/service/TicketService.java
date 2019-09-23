package team.components.cinema.service;

import org.springframework.stereotype.Service;
import team.components.cinema.dto.TicketDTO;
import team.components.cinema.model.Ticket;
import team.components.cinema.model.User;
import team.components.cinema.repository.TicketRepository;
import team.components.cinema.repository.UserRepository;

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

    public Iterable<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

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
