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
@EqualsAndHashCode(callSuper = true)
public class CartDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private Double total;
}
