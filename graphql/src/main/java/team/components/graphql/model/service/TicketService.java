package team.components.graphql.model.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team.components.graphql.model.Ticket;

import java.util.List;

@Service
public class TicketService {
    private final RestTemplate restTemplate = new RestTemplate();

    public Ticket getTicketById(Long id) {
        String queryUrl = "http://localhost:8089/api/tickets/" + id;
        return restTemplate.getForObject(queryUrl, Ticket.class);
    }

    public List<Ticket> getAllTickets() {
        String queryUrl = "http://localhost:8089/tickets";

        ResponseEntity<List<Ticket>> responseEntity = restTemplate.exchange(queryUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ticket>>() {
                });

        return responseEntity.getBody();
    }

    public void deleteTicketById(Long id) {
        String queryUrl = "http://localhost:8089/api/tickets/" + id;
        restTemplate.delete(queryUrl);
    }
}
