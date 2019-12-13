package team.components.cinema.model.util.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import team.components.cinema.model.entity.Ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RemoteTicketMapper implements Mapper {
    private final ObjectMapper mapper;

    public RemoteTicketMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Iterable<Ticket> jsonToTickets(JSONArray array) {
        List<Ticket> tickets = new ArrayList<>();
        mapper.registerModule(new JavaTimeModule());

        for (int i = 0; i < array.length(); i++) {
            JSONObject ticket = array.getJSONObject(i);
            JSONObject ticketNew = internalMapper(ticket);

            try {
                tickets.add(mapper.readValue(ticketNew.toString(), Ticket.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tickets;
    }

    public Ticket jsonToTicket(JSONObject ticket) {
        JSONObject ticketNew = internalMapper(ticket);
        mapper.registerModule(new JavaTimeModule());

        try {
            return mapper.readValue(ticketNew.toString(), Ticket.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private JSONObject internalMapper(JSONObject ticket) {
        JSONObject ticketNew = new JSONObject();

        ticketNew.put("id", ticket.getInt("id"));
        ticketNew.put("seat", ticket.getInt("seat"));
        ticketNew.put("price", ticket.getInt("price"));
        ticketNew.put("owner", ticket.optJSONObject("owner"));

        JSONObject session = new JSONObject();
        session.put("id", ticket.getJSONObject("session").getInt("id"));
        String cinemaName = ticket
                .getJSONObject("session")
                .getJSONObject("cinema")
                .getString("name");
        session.put("cinema", cinemaName);
        session.put("movie", ticket.getJSONObject("session").getString("movie"));
        session.put("startTime", ticket.getJSONObject("session").getString("startTime"));
        session.put("hall", ticket.getJSONObject("session").getString("hall"));

        ticketNew.put("session", session);

        return ticketNew;
    }
}

