package datnnhom12api.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthDTO implements Serializable {
    private String token;
    private String name;
    private List<RoleDTO> roles;
    private String username;
}
