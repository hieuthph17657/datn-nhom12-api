package datnnhom12api.request;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailRequest {
    @NotNull (message = "id sản phẩm không được để trống")
    private Long productId;

    @NotNull (message = "id đơn hàng không được để trống")
    private int money;

    @NotNull (message = "số lượng không được để trống")
    private int quantity;

    @NotNull (message = "Status không được để trống")
    private int status;
}
