package datnnhom12api.request;

import datnnhom12api.utils.support.ReturnStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReturnRequest {

    @NotNull(message = "Id sản phẩm không được trống")
    private Long productId;

    @NotNull(message = "Id hoá đơn không được trống")
    private Long orderId;

    @NotBlank(message = "Lý do huỷ đơn không được để trống")
    private String reason;

    private String description;

    private ReturnStatus status;


}
