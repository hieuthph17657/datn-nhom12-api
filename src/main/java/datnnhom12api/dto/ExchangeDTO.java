package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.ReturnStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExchangeDTO extends BaseDTO implements Serializable {

    private Long id;

    private Long orderId;

    private String reason;

    private String description;

    private String isCheck;

    private ReturnStatus status;

//    private List<ReturnDetailDTO> returnDetailEntities;
}