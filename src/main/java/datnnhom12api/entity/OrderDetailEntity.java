package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.OrderDetailRequest;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_category")
@EqualsAndHashCode(callSuper = true)
public class OrderDetailEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long productId;
    private Long orderId;
    private int money;
    private int quantity;
    private int sale;

    public void setData(OrderDetailRequest request){
        this.productId = request.getProductId();
        this.orderId = request.getOrderId();
        this.money = request.getMoney();
        this.quantity = request.getQuantity();
        this.sale = request.getSale();
    }
}
