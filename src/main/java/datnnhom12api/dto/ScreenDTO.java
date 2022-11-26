package datnnhom12api.dto;


import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.ScreenStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ScreenDTO extends BaseDTO implements Serializable {

    private Long id;

    private String size;

    private String screenTechnology;

    private String resolution;

    private String screenType;

    private String backgroundPanel;

    private String brightness;

    private String colorCoverage;

    private String touchScreen;

    private double price;

    private ScreenStatus status;
}
