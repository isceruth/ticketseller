package team.components.cinema.controller;

import org.json.JSONObject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.service.TicketInformation;
import team.components.cinema.model.specification.TicketIsNotClaimed;
import team.components.cinema.model.specification.TicketNearSeat;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("tickets")
public class AggregationController {
    private final TicketInformation combinedTicketService;

    public AggregationController(TicketInformation combinedTicketService) {
        this.combinedTicketService = combinedTicketService;
    }

    @GetMapping
    Iterable<Ticket> getTickets(@RequestParam(value = "nearSeat", required = false) Long seat,
                                @RequestParam(value = "isClaimed", required = false) Boolean claimed) {
        if (seat == null && claimed == null) {
            return combinedTicketService.findAllTickets();
        }

        Specification<Ticket> spec = new TicketNearSeat(seat)
                .and(new TicketIsNotClaimed(claimed));

        JSONObject params = new JSONObject();
        params.put("nearSeat", seat);
        params.put("isClaimed", claimed);

        return combinedTicketService.findAllTickets(spec, params);
    }
}
