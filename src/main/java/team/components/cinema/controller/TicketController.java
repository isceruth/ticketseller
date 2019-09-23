package team.components.cinema.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import team.components.cinema.dto.TicketDTO;
import team.components.cinema.model.Ticket;
import team.components.cinema.service.TicketService;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("{id}")
    Ticket getTicketById(@PathVariable("id") long id) {
        return ticketService.findTicketById(id).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping
    Iterable<Ticket> getAllTickets() {
        return ticketService.findAllTickets();
    }

    @DeleteMapping("{id}")
    void deleteTicket(@PathVariable long id) {
        ticketService.deleteTicketById(id);
    }

    @PostMapping
    ResponseEntity<Object> createTicket(@RequestBody TicketDTO ticket) {
        Ticket newTicket = ticketService.createTicket(ticket);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(newTicket.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

   @PutMapping("{id}")
    ResponseEntity<Object> updateTicket(@PathVariable long id, @RequestParam(name = "owner") long ownerId) {
        Optional<Ticket> ticketForUpdate = ticketService.findTicketById(id);

        if (!ticketForUpdate.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ticketService.changeOwner(id, ownerId);

        return ResponseEntity.noContent().build();
    }
}
