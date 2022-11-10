package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.ReturnRequest;
import datnnhom12api.utils.support.ProductStatus;
import datnnhom12api.utils.support.ReturnStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "returns")
public class ReturnEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private ReturnStatus status;

    public void setData(ReturnRequest request){
        this.productId = request.getProductId();
        this.orderId = request.getOrderId();
        this.reason = request.getReason();
        this.description=request.getDescription();
        this.status = request.getStatus();
    }
}
