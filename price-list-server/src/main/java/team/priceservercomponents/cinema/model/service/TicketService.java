package team.priceservercomponents.cinema.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team.priceservercomponents.cinema.model.dto.PagedPriceListDto;
import team.priceservercomponents.cinema.model.entity.Ticket;
import team.priceservercomponents.cinema.model.repository.TicketRepository;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService implements TicketInformation {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Iterable<Ticket> findAllTickets() {
        return null;
    }

    public PagedPriceListDto findAllSimpleTickets(Pageable pageable) {
        Page<Ticket> page = ticketRepository.findAll(pageable);

        Map<Long, Long> priceListMap = page.get()
                .collect(Collectors.toMap(Ticket::getId, Ticket::getPrice));

        PagedPriceListDto dto = new PagedPriceListDto();
        dto.setCurrentPage((long) pageable.getPageNumber());
        dto.setTotalPages((long) page.getTotalPages());
        dto.setPageSize((long) pageable.getPageSize());
        dto.setResults(priceListMap);

        return dto;
    }

    @Override
    public Optional<Ticket> findTicketById(long id) {
        return ticketRepository.findById(id);
    }
}
