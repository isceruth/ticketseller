package team.components.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagedSimpleTicketsDTO {
    private Long totalPages;
    private Long currentPage;
    private Long pageSize;

    private Map<Long, Long> results;
}
