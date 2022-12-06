package datnnhom12api.request;

import datnnhom12api.utils.support.WinStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WinRequest {
    @NotBlank(message = "tên win không được trống")
    private String name;
    @Enumerated(EnumType.STRING)
    private WinStatus status;
}
