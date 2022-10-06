package datnnhom12api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    @NotBlank(message = "tên loại sản phẩm không được để trống")
    private String name;
    @NotNull(message = "trạng thái không được để trống")
    private int active;
}
