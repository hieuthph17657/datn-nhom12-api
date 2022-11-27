package datnnhom12api.dto;

import datnnhom12api.entity.CardEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GraphicsDTO  implements Serializable {

    private Long id;

    private CardEntity card_onboard;

    private CardEntity card_discrete;
}
