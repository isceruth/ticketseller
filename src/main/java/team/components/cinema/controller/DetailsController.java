package team.components.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.service.RemoteTicketService;

@RestController
@RequestMapping("/details")
public class DetailsController {
    private final RemoteTicketService remoteTicketService;

    public DetailsController(RemoteTicketService remoteTicketService) {
        this.remoteTicketService = remoteTicketService;
    }

    @GetMapping("{id}")
    Ticket getTicketById(@PathVariable("id") int id) {
        return remoteTicketService.findTicketById(id).get();
    }
}
