package datnnhom12api.request;

import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.utils.support.ReturnDetailStatus;
import datnnhom12api.utils.support.ReturnStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReturnDetailRequest {

    @NotNull(message = "Mã sản phầm không được trống")
    private Long productId;

    private Long orderDetailId;

    private int quantity;

    private  Long id;

    private ReturnDetailStatus status;

}
