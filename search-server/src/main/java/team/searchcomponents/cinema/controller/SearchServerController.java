package team.searchcomponents.cinema.controller;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.searchcomponents.cinema.model.entity.Ticket;
import team.searchcomponents.cinema.model.service.TicketInformation;
import team.searchcomponents.cinema.model.specifications.TicketIsNotClaimed;
import team.searchcomponents.cinema.model.specifications.TicketNearSeat;

@RestController
@RequestMapping("/search")
public class SearchServerController {
    private final TicketInformation ticketService;

    public SearchServerController(TicketInformation ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    Iterable<Ticket> getTickets(@RequestParam(value = "nearSeat", required = false) Long seat,
                                @RequestParam(value = "isClaimed", required = false) Boolean claimed) {
        Specification<Ticket> spec = new TicketNearSeat(seat)
                .and(new TicketIsNotClaimed(claimed));

        return ticketService.findAllTickets(spec);
    }

    @GetMapping("all")
    Iterable<Ticket> getAllTickets() {
        return ticketService.findAllTickets();
    }
}
