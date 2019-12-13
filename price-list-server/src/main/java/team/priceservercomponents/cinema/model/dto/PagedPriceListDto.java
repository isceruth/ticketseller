package team.priceservercomponents.cinema.model.dto;

import java.util.Map;

public class PagedPriceListDto {
    private Long totalPages;
    private Long currentPage;
    private Long pageSize;

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Map<Long, Long> getResults() {
        return results;
    }

    public void setResults(Map<Long, Long> results) {
        this.results = results;
    }

    private Map<Long, Long> results;
}