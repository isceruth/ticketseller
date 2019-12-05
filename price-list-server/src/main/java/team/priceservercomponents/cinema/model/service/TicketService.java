package team.priceservercomponents.cinema.model.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team.priceservercomponents.cinema.model.dto.TicketDTO;
import team.priceservercomponents.cinema.model.entity.Ticket;
import team.priceservercomponents.cinema.model.entity.User;
import team.priceservercomponents.cinema.model.repository.TicketRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class TicketService implements TicketInformation {
    private final TicketRepository ticketRepository;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    @Override
    public Iterable<Ticket> findAllTickets() {
        delayResponseInSeconds(15, 20);
        return ticketRepository.findAll();
    }

    @Override
    public Iterable<Ticket> findAllTickets(Specification<Ticket> specs) {
        delayResponseInSeconds(15, 20);
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

    private void delayResponseInSeconds(int minDelay, int maxDelay) {
        try {
            Thread.sleep(generateResponseDelayInSeconds(minDelay, maxDelay) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int generateResponseDelayInSeconds(int min, int max) {
        Random random = new Random();
        ++max;
        return random.nextInt(max - min) + min;
    }
}
