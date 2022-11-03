package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.utils.support.OrderStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends BaseDTO implements Serializable {
    private Long id;

    private Long userId;

    private int total;

    private String payment;

    private String address;

    private OrderStatus status;

    private String note;

    private List<OrderDetailEntity> orderDetails;
}

