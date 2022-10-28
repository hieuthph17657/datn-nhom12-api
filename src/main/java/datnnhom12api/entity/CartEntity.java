package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.CartRequest;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
@EqualsAndHashCode(callSuper = true)
public class CartEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private Double total;

    public void setData(CartRequest request) {
        this.productId = request.getProductId();
        this.quantity = request.getQuantity();
    }
}
