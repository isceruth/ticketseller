package team.components.cinema.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team.components.cinema.model.dto.PagedSimpleTicketsDTO;
import team.components.cinema.model.dto.SimpleTicket;
import team.components.cinema.model.dto.TicketDTO;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.entity.User;
import team.components.cinema.model.repository.TicketRepository;
import team.components.cinema.model.util.SimpleTicketMapper;

import java.util.*;
import java.util.stream.Collectors;

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
        return ticketRepository.findAll();
    }

    @Override
    public Iterable<Ticket> findAllTickets(Specification<Ticket> specs) {
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

    public PagedSimpleTicketsDTO findAllSimpleTickets(Pageable pageable) {
        Page<Ticket> page = ticketRepository.findAll(pageable);

        Map<Long, Long> priceListMap = page.get()
                .collect(Collectors.toMap(Ticket::getId, Ticket::getPrice));

        return PagedSimpleTicketsDTO.builder()
                .currentPage((long) pageable.getPageNumber())
                .totalPages((long) page.getTotalPages())
                .pageSize((long) pageable.getPageSize())
                .results(priceListMap)
                .build();
    }
}
