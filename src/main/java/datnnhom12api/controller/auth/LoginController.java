package datnnhom12api.controller.auth;

import datnnhom12api.dto.AuthDTO;
import datnnhom12api.dto.RoleDTO;
import datnnhom12api.entity.RoleEntity;
import datnnhom12api.request.AuthRequest;
import datnnhom12api.response.AuthResponse;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.jwt.JwtTokenProvider;
import datnnhom12api.jwt.user.CustomUserDetails;
import datnnhom12api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public AuthResponse authenticateUser(@Valid @RequestBody AuthRequest loginRequest) throws CustomException {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserEntity userEntity = userRepository.findByUsername(authentication.getName());
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            AuthDTO authDTO = new AuthDTO();
            authDTO.setToken(jwt);
            authDTO.setName(userEntity.getName());
            authDTO.setUsername(userEntity.getUsername());
            List<RoleDTO> list = new ArrayList<>();
            for (RoleEntity e : userEntity.getRoles()) {
                list.add(new RoleDTO(e.getId(), e.getName(), e.getCode()));
            }
            authDTO.setRoles(list);
            return new AuthResponse(authDTO);
        } catch (Exception e) {
            throw new CustomException(403, "Đăng nhập thất bại kiểm tra lại tài khoản mật khẩu");
        }
    }
}
