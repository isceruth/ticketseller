package team.components.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.service.TicketInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tickets")
public class AggregationController {
    private final List<TicketInformation> ticketServices;

    public AggregationController(List<TicketInformation> ticketServices) {
        this.ticketServices = ticketServices;
    }

    @GetMapping
    Iterable<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        for (TicketInformation service : ticketServices) {
            Iterable<Ticket> ticketsFromService = service.findAllTickets();
            ticketsFromService.forEach(tickets::add);
        }

        return tickets;
    }
}
