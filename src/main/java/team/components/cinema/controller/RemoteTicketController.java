package team.components.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.components.cinema.model.dto.SimpleTicket;
import team.components.cinema.model.service.RemoteTicketService;
import team.components.cinema.model.util.SimpleTicketMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/price-list")
public class RemoteTicketController {
    private final RemoteTicketService remoteTicketService;

    public RemoteTicketController(RemoteTicketService remoteTicketService) {
        this.remoteTicketService = remoteTicketService;
    }

    @GetMapping
    List<SimpleTicket> getAllTickets() {
        return remoteTicketService.findAllTickets().stream()
                .map(SimpleTicketMapper::toSimpleTicket)
                .collect(Collectors.toList());
    }
}
