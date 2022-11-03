package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.OrderRequest;
import datnnhom12api.utils.support.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
@EqualsAndHashCode(callSuper = true)
public class OrderEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long userId;
    private double total;
    private String payment;
    private String address;
    private String note;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    //    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetailEntity> orderDetails;

    public void setData(OrderRequest request) {
        this.userId = request.getUserId();
        this.total = request.getTotal();
        this.payment = request.getPayment();
        this.address = request.getAddress();
        this.note = request.getNote();
        this.status = request.getStatus() == OrderStatus.DA_DAT ? OrderStatus.DA_DAT : OrderStatus.DA_HUY;
    }
}

