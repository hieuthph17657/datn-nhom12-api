package datnnhom12api.entity;
import datnnhom12api.dto.core.BaseEntity;
import datnnhom12api.request.OrderRequest;
import lombok.*;

import javax.persistence.*;

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
    private int total;
    private String payment;
    private String address;
    private int status;

    public void setData(OrderRequest request) {
        this.userId = request.getUserId();
        this.total = request.getTotal();
        this.payment = request.getPayment();
        this.address = request.getAddress();
        this.status = request.getStatus();
    }
}

