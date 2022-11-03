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

    private String phone;

    private String customerName;
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
        this.phone = request.getPhone().equals(" ") ? "" : request.getPhone();
        this.customerName = request.getCustomerName().equals(" ") ? " " : request.getCustomerName();
        this.note = request.getNote();
        if (request.getStatus() == OrderStatus.CHO_XAC_NHAN){
            this.status = OrderStatus.CHO_XAC_NHAN;
        } else if (request.getStatus() == OrderStatus.CHO_LAY_HANG) {
            this.status = OrderStatus.CHO_LAY_HANG;
        } else if (request.getStatus() == OrderStatus.DANG_GIAO) {
            this.status = OrderStatus.DANG_GIAO;
        } else if (request.getStatus() == OrderStatus.DA_NHAN) {
            this.status = OrderStatus.DA_NHAN;
        } else if (request.getStatus() == OrderStatus.DA_HUY) {
            this.status = OrderStatus.DA_HUY;
        }
    }
}
