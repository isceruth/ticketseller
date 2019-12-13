package team.priceservercomponents.cinema.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import team.priceservercomponents.cinema.model.dto.PagedPriceListDto;
import team.priceservercomponents.cinema.model.entity.Ticket;
import team.priceservercomponents.cinema.model.service.TicketInformation;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/price-list")
public class PriceServerController {
    private final TicketInformation ticketService;

    public PriceServerController(TicketInformation ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("{id}")
    Ticket getTicketById(@PathVariable("id") int id) {
        return ticketService.findTicketById(id).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping
    PagedPriceListDto getPriceList(@PageableDefault(size = 5000, sort = "id") Pageable pageable) {
        Pageable modifiedPageable = PageRequest.of(pageable.getPageNumber(), 5000);
        return ticketService.findAllSimpleTickets(pageable);
    }
}
