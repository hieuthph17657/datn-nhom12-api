package datnnhom12api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;
    @NotNull(message = "Số lượng sản phẩm không được để trống")
    private int quantity;
    @NotNull(message = "Giá sản phẩm không được để trống")
    private Float price;
//    @NotBlank(message = "Mã loại sản phẩm không được để trống")
//    private Long categoryId;
    ///
    @NotNull(message = "Trạng thái không được để trống")
    private int status;
}
