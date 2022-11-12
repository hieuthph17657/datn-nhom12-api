package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.ReturnRequest;
import datnnhom12api.utils.support.ProductStatus;
import datnnhom12api.utils.support.ReturnStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "description")
    private String description;

    @Column(name = "is_check")
    private String isCheck;

    @Enumerated(EnumType.STRING)
    private ReturnStatus status;

    @OneToMany(mappedBy = "Return")
    List<ReturnDetailEntity> returnDetailEntities;

    public void setData(ReturnRequest request){
        this.isCheck = request.getIsCheck();
        this.orderId = request.getOrderId();
        this.reason = request.getReason();
        this.description=request.getDescription();
        this.status = request.getStatus();
    }
}