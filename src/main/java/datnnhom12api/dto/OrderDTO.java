package datnnhom12api.dto;

import datnnhom12api.dto.core.BaseDTO;
import lombok.*;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long userId;
    private int total;
    private String payment;
    private String address;
    private int status;
}

