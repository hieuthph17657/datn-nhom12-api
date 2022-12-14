package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.OrderStatus;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OrderByIdDTO extends BaseDTO implements Serializable {
    private Long id;

//    private Long userId;

    private int total;

    private String payment;

    private double money;

    private String address;

    private OrderStatus status;

    private String note;

    private String customerName;

    private String phone;

    private UserDTO user;

    private List<OrderDetailDTO> orderDetails;

    private double shippingFree;
}
