package datnnhom12api.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailDTO implements Serializable {
    private Long id;
    private Long productId;
    private Long orderId;
    private int money;
    private int quantity;
    private int sale;
}
