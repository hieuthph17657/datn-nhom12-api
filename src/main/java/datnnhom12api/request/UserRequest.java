package datnnhom12api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
    @NotBlank(message = "tài khoản không được để trống")
    private String username;
    @NotBlank(message = "mật khẩu không được để trống")
    private String password;
    @NotBlank(message = "email không được để trống")
    private String email;
    @NotBlank(message = "tên không được để trống")
    private String name;
    @NotBlank(message = "số điện thoại không được để trống")
    private String phone;
    @NotNull(message = "trạng thái không được để trống")
    private int status;
}
