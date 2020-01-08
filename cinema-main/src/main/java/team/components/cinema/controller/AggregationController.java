package team.components.cinema.controller;

import org.json.JSONObject;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.service.CombinedTicketService;
import team.components.cinema.model.service.TicketInformation;
import team.components.cinema.model.specification.TicketIsNotClaimed;
import team.components.cinema.model.specification.TicketNearSeat;

@RestController
@RequestMapping("tickets")
public class AggregationController {
    private final TicketInformation combinedTicketService;

    public AggregationController(TicketInformation combinedTicketService) {
        this.combinedTicketService = combinedTicketService;
    }

    @GetMapping
    Iterable<Ticket> getTickets(@RequestParam(value = "nearSeat", required = false) Long seat,
                                @RequestParam(value = "isClaimed", required = false) Boolean claimed,
                                @RequestParam(value = "cinema", required = false) String cinema,
                                @RequestParam(value = "place", required = false) Long place) {
        if (cinema != null && place != null) {
            return ((CombinedTicketService) combinedTicketService).getTicketsForCinemaAndPlace(cinema, place);
        }

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
