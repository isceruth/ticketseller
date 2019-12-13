package team.components.cinema.model.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import team.components.cinema.model.entity.Ticket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class CombinedTicketService implements TicketInformation {
    private final ThreadPoolTaskExecutor executor;
    private final TicketInformation ticketService;
    private final TicketInformation pricelistTicketService;
    private final TicketInformation searchTicketService;

    public CombinedTicketService(ThreadPoolTaskExecutor executor, TicketInformation ticketService, TicketInformation pricelistTicketService, TicketInformation searchTicketService) {
        this.executor = executor;
        this.ticketService = ticketService;
        this.pricelistTicketService = pricelistTicketService;
        this.searchTicketService = searchTicketService;
    }

    @Override
    public Iterable<Ticket> findAllTickets() {
        Future<Iterable<Ticket>> ticketServiceFuture = executor
                .submit((Callable<Iterable<Ticket>>) ticketService::findAllTickets);
        Future<Iterable<Ticket>> pricelistTicketServiceFuture = executor
                .submit((Callable<Iterable<Ticket>>) pricelistTicketService::findAllTickets);
        Future<Iterable<Ticket>> searchTicketServiceFuture = executor
                .submit((Callable<Iterable<Ticket>>) searchTicketService::findAllTickets);

        return getTicketsFromFuture(ticketServiceFuture, pricelistTicketServiceFuture, searchTicketServiceFuture);
    }

    @Override
    public Iterable<Ticket> findAllTickets(Specification<Ticket> specs) {
        return null;
    }

    @Override
    public Optional<Ticket> findTicketById(long id) {
        return Optional.empty();
    }

    private List<Ticket> getTicketsFromFuture(Future<Iterable<Ticket>>... futures) {
        List<Ticket> result = new ArrayList<>();
        try {
            for (Future<Iterable<Ticket>> future : futures) {
                result.addAll((Collection<? extends Ticket>) future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
