package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class accessoryDTO extends BaseDTO implements Serializable {

    private Long id;

    private String name;

    private Long categoryId;

    private double price;

    private String description;
}
