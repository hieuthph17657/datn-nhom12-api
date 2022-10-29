package datnnhom12api.request;

import datnnhom12api.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserOnOrderRequest {
    @NotBlank(message = "tài khoản không được để trống")
    private String username;
    private String newPassword;
    @NotBlank(message = "tên không được để trống")
    private String fullName;
    @NotBlank(message = "email không được để trống")
    @Email
    private String email;
    @NotBlank(message = "số điện thoại không được để trống")
    private String phoneNumber;
    @NotBlank(message = "địa chỉ không được để trống")
    private String address;
}
