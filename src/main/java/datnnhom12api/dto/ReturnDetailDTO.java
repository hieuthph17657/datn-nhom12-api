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
public class ReturnDetailDTO extends BaseDTO implements Serializable {
    private Long id;

    private Long productId;

    private Long orderDetailId;

    private Long returnId;

    private int quantity;
}
