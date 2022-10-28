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
@Table(name = "cart")
@EqualsAndHashCode(callSuper = true)
public class CartEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total")
    private Double total;

    public void setData(CartRequest request) {
        this.productId = request.getProductId();
        this.userId = request.getUserId();
        this.quantity = request.getQuantity();
        this.total = request.getTotal();
    }
}
