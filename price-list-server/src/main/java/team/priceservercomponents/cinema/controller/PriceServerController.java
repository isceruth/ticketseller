package team.priceservercomponents.cinema.controller;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import team.priceservercomponents.cinema.model.entity.Ticket;
import team.priceservercomponents.cinema.model.service.TicketInformation;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/search")
public class PriceServerController {
    private final TicketInformation ticketService;

    public PriceServerController(TicketInformation ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("{id}")
    Ticket getTicketById(@PathVariable("id") int id) {
        return ticketService.findTicketById(id).orElseThrow(NoSuchElementException::new);
    }
}
