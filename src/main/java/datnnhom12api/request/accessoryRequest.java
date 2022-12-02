package datnnhom12api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class accessoryRequest {
    @NotBlank(message = "Tên phụ kiện không được để trống")
    private String name;

    @NotBlank(message = "Loại phụ kiện không được để trống")
    private String type;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotNull(message = "Giá tiền không được để trống")
    private double price;
}
