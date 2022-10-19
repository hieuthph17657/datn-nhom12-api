package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "order_detail")
@EqualsAndHashCode(callSuper = true)
public class OrderDetailEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long productId;
    private int money;
    private int quantity;
    private int status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;

    public void setData(OrderDetailRequest request){
        this.productId = request.getProductId();
        this.money = request.getMoney();
        this.quantity = request.getQuantity();
        this.status = request.getStatus();
    }
}
