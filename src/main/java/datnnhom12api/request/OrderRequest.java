package datnnhom12api.request;

import datnnhom12api.entity.OrderDetailEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {
    @NotNull(message = "id người dùng không được để trống")
    private Long userId;

    @NotNull(message = "tổng tiền không được để trống")
    private int total;

    @NotBlank(message = "phương thức thanh toán không được để trống")
    private String payment;

    @NotBlank(message = "địa chỉ không được để trống")
    private String address;

    @NotNull(message = "trạng thái không được để trống")
    private int status;

    @NotEmpty(message = "ORDER_DETAIL_REQUIRED")
    @Size(min = 1, message = "ORDER_DETAIL_SIZE")
    private List<OrderDetailEntity> orderDetails;
}
