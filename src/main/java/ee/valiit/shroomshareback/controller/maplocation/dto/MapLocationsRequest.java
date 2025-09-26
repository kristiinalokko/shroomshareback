package ee.valiit.shroomshareback.controller.maplocation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapLocationsRequest {
    private Integer shroomId;
    private BigDecimal minRating;
    private LocalDate lastActive;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer radiusKm;
}
