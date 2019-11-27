package team.components.cinema.model.util;

import org.springframework.stereotype.Component;
import team.components.cinema.model.entity.Session;
import team.components.cinema.model.entity.Ticket;
import team.components.cinema.model.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class InformationGenerator {
    private final User user = new User();
    private final Session session = new Session();

    {
        user.setEmail("test@test.com");
        user.setFirstName("John");
        user.setLastName("Johnson");
        user.setId(-1L);
        user.setPhoneNumber("+399999999999");

        session.setCinema("Test cinema");
        session.setHall("Test hall");
        session.setId(-1L);
        session.setMovie("Matrix: Reboot");
        session.setStartTime(LocalDateTime.now());
    }

    public void generateTickets(List<Ticket> tickets, int n) {
        while (n > 0) {
            Ticket.Builder ticketBuilder = new Ticket.Builder()
                    .setSession(session)
                    .setPrice(Math.round(Math.random() * 1000))
                    .setSeat(Math.round(Math.random() * 250));
            if (n % 2 == 0) {
                ticketBuilder.setOwner(null);
            } else {
                ticketBuilder.setOwner(user);
            }
            tickets.add(ticketBuilder.build());
            n--;
        }
    }
}
