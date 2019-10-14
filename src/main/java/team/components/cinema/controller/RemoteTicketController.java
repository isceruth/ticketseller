package team.components.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.components.cinema.model.dto.SimpleTicket;
import team.components.cinema.model.service.TicketService;
import team.components.cinema.model.util.SimpleTicketMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/price-list")
public class RemoteTicketController {
    private final TicketService remoteTicketService;

    public RemoteTicketController(TicketService remoteTicketService) {
        this.remoteTicketService = remoteTicketService;
    }

    @GetMapping
    Iterable<SimpleTicket> getAllTickets() {
        return remoteTicketService.findAllSimpleTickets();
    }
}
