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

import java.util.Optional;

@Service
public class SearchTicketService implements TicketInformation {
    private final String url;
    private final RestTemplate restTemplate;
    private final RemoteTicketMapper remoteTicketMapper;

    public SearchTicketService(@Value("${search.server.url}") String url, RestTemplate restTemplate, RemoteTicketMapper remoteTicketMapper) {
        this.url = url;
        this.restTemplate = restTemplate;
        this.remoteTicketMapper = remoteTicketMapper;
    }

    @Override
    @Cacheable(value = "searchServiceTickets")
    public Iterable<Ticket> findAllTickets() {
        String finalUrl = url + "/search";

        String result = getResultFromServer(finalUrl);

        return remoteTicketMapper.jsonToTickets(new JSONArray(result));
    }

    @Override
    @Cacheable(value = "searchServiceTickets", key = "#params.toString()")
    public Iterable<Ticket> findAllTickets(Specification<Ticket> specs, JSONObject params) {
        StringBuilder buildUrl = new StringBuilder(url)
                .append("/search")
                .append(params.get("nearSeat") == null ? "" : "?nearSeat=" + params.getInt("nearSeat"));
        if (buildUrl.charAt(buildUrl.length() - 1) == 'h') {
            buildUrl.append("?isClaimed=").append(params.getBoolean("isClaimed"));
        }
        buildUrl.append(params.get("isClaimed") == null ? "" : "&isClaimed=" + params.getBoolean("isClaimed"));

        String result = getResultFromServer(buildUrl.toString());

        return remoteTicketMapper.jsonToTickets(new JSONArray(result));
    }

    @Override
    public Optional<Ticket> findTicketById(long id) {
        return Optional.empty();
    }

    private String getResultFromServer(String url) {
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        return result.getBody();
    }
}
