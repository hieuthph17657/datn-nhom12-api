package datnnhom12api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountDTO implements Serializable {
    private Long id;
    private String name;
    private Double ratio;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int active;
}