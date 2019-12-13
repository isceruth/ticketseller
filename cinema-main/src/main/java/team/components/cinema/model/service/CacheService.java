package team.components.cinema.model.service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CacheService {
    private final CacheManager cacheManager;
    private final TicketInformation ticketService;
    private final TicketInformation pricelistTicketService;
    private final TicketInformation searchTicketService;

    public CacheService(CacheManager cacheManager, TicketInformation ticketService, TicketInformation pricelistTicketService, TicketInformation searchTicketService) {
        this.cacheManager = cacheManager;
        this.ticketService = ticketService;
        this.pricelistTicketService = pricelistTicketService;
        this.searchTicketService = searchTicketService;
    }

    @Scheduled(cron = "0 0 */1 * * ?")
    public void refreshCacheScheduled() {
        for (String name : cacheManager.getCacheNames()) {
            Optional.ofNullable(cacheManager.getCache(name)).ifPresent(Cache::clear);
        }
        ticketService.findAllTickets();
        pricelistTicketService.findAllTickets();
        searchTicketService.findAllTickets();
    }

    public void initCache() {
        System.out.println("Initing cache!");
        ticketService.findAllTickets();
        pricelistTicketService.findAllTickets();
        searchTicketService.findAllTickets();
        System.out.println("Cache initted!");
    }

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initCache();
    }
}
