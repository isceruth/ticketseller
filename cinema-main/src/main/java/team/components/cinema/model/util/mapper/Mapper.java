package team.components.cinema.model.util.mapper;

import org.json.JSONArray;
import team.components.cinema.model.entity.Ticket;

public interface Mapper {
    Iterable<Ticket> jsonToTickets(JSONArray array);
}
