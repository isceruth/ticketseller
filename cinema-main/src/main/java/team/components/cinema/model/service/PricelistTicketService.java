package team.components.cinema.model.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.util.mapper.RemoteTicketMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PricelistTicketService implements TicketInformation {
    private final String url;
    private final RestTemplate restTemplate;
    private final RemoteTicketMapper remoteTicketMapper;

    public PricelistTicketService(@Value("${pricelist.server.url}") String url, RestTemplate restTemplate, RemoteTicketMapper remoteTicketMapper) {
        this.url = url;
        this.restTemplate = restTemplate;
        this.remoteTicketMapper = remoteTicketMapper;
    }

    @Override
    @Cacheable(value = "pricelistServiceTickets")
    public Iterable<Ticket> findAllTickets() {
        String finalUrl = url + "/price-list";

        JSONObject result = new JSONObject(getResultFromServer(finalUrl));
        List<Ticket> tickets = new ArrayList<>();

        if (result.getInt("totalPages") > 1) {
            int totalPages = result.getInt("totalPages");
            int currPage = 0;
            while (totalPages - currPage > 1) {
                finalUrl += "?page=" + currPage;
                result = new JSONObject(getResultFromServer(finalUrl));
                tickets.addAll(mapPriceListToTicketList(result.getJSONObject("results")));
                currPage++;
            }

            return tickets;
        }

        return mapPriceListToTicketList(result.getJSONObject("results"));
    }

    @Override
    @Cacheable(value = "pricelistServiceTickets", key = "#params.toString()")
    public Iterable<Ticket> findAllTickets(Specification<Ticket> specs, JSONObject params) {
        Iterable<Ticket> result = findAllTickets();

        return StreamSupport.stream(result.spliterator(), false)
                .filter(ticket -> params.get("nearSeat") == null || Math.abs(ticket.getSeat() - params.getInt("nearSeat")) < 2)
                .filter(ticket -> params.get("isClaimed") == null || params.getBoolean("isClaimed") == (ticket.getOwner() != null))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> findTicketById(long id) {
        return Optional.empty();
    }

    private String getResultFromServer(String url) {
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        return result.getBody();
    }

    private List<Ticket> mapPriceListToTicketList(JSONObject result) {
        List<Ticket> tickets = new ArrayList<>();

        for (Iterator<String> it = result.keys(); it.hasNext(); ) {
            String key = it.next();

            String finalUrl = url + "/price-list/" + key;

            String object = getResultFromServer(finalUrl);

            tickets.add(remoteTicketMapper.jsonToTicket(new JSONObject(object)));
        }

        return tickets;
    }
}
