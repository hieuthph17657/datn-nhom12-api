package datnnhom12api.request.products;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductPropertyRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String propertyName;

    @NotBlank(message = "Giá trị sản phẩm không được để trống")
    private String propertyValue;
}
