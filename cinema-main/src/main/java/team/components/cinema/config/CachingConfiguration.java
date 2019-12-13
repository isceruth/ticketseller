package team.components.cinema.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@EnableCaching
@Configuration
public class CachingConfiguration {
    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        Set<String> hash = new HashSet<>();
        hash.add("tickets");
        hash.add("searchServiceTickets");
        hash.add("pricelistServiceTickets");
        concurrentMapCacheManager.setCacheNames(hash);
        return concurrentMapCacheManager;
    }
}
