package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.ProductEntity;
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

    private ProductEntity productId;

    private OrderDetailEntity orderDetailId;

    private Long returnId;

    private int quantity;
}
