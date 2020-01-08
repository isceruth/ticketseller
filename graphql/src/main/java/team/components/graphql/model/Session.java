package team.components.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {
    private Long id;
    private String cinema;
    private String movie;
    private LocalDateTime startTime;
    private String hall;
}
