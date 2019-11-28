package team.components.cinema.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.components.cinema.model.dto.PagedSimpleTicketsDTO;
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
    public PagedSimpleTicketsDTO getPriceList(@PageableDefault(size = 5000, sort = "id") Pageable pageable) {
        Pageable modifiedPageable = PageRequest.of(pageable.getPageNumber(), 5000);
        System.out.println("PriceController");
        return remoteTicketService.findAllSimpleTickets(modifiedPageable);
    }
}
