package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.utils.support.ReturnStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ReturnDTO extends BaseDTO implements Serializable {

    private Long id;

    private Long productId;

    private OrderEntity orderId;

    private OrderDetailEntity orderDetailEntity;

    private String reason;

    private String description;

    private ReturnStatus status;
}
