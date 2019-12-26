package team.componenets.bookingservice.model.service;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team.componenets.bookingservice.model.dto.BookTicketDto;
import team.componenets.bookingservice.model.entity.Ticket;
import team.componenets.bookingservice.model.repository.TicketRepository;

@Service
public class BookingService {
    private final TicketRepository ticketRepository;

    @Value("${main.server.url}")
    private String url;

    public BookingService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public boolean bookTicket(BookTicketDto dto) {
        String finalUrl = url + "/tickets" + "?cinema=" + dto.getCinema() + "&place=" + dto.getPlace();
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForEntity(finalUrl, String.class).getBody();
        JSONArray jsonArray = new JSONArray(result);

        if (jsonArray.length() != 1) return false;
        ticketRepository.save(formTicket(dto));
        return true;
    }

    private Ticket formTicket(BookTicketDto dto) {
        Ticket ticket = new Ticket();
        ticket.setCinema(dto.getCinema());
        ticket.setEmail(dto.getEmail());
        ticket.setPlace(dto.getPlace());
        ticket.setFirstname(dto.getFirstname());
        ticket.setLastname(dto.getLastname());
        ticket.setPhone(dto.getPhone());
        return ticket;
    }
}
