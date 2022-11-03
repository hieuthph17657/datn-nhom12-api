package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.OrderRequest;
import datnnhom12api.utils.support.OrderDetailStatus;
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
        if (request.getStatus().equals(" ")) {
            this.status = OrderStatus.CHO_XAC_NHAN;
        } else if (request.getStatus().equals("Chờ lấy hàng")) {
            this.status = OrderStatus.CHO_LAY_HANG;
        } else if (request.getStatus().equals("Đang giao")) {
            this.status = OrderStatus.DANG_GIAO;
        } else if (request.getStatus().equals("Đã nhận")) {
            this.status = OrderStatus.DA_NHAN;
        } else if (request.getStatus().equals("Đã huỷ")) {
            this.status = OrderStatus.DA_HUY;
        }
    }
}

