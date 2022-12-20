package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private double total;
    private String payment;
    private double money;
    private String address;

    @Column(name = "shipping_free")
    private double shippingFree;
    private String phone;
    private String customerName;
    private String note;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    //    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetailEntity> orderDetails;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    public void setData(OrderRequest request) {
        this.total = request.getTotal();
        this.payment = request.getPayment();
        this.money = request.getMoney();
        this.address = request.getAddress();
        this.phone = request.getPhone();
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
        }else if(request.getStatus() == OrderStatus.CHUA_THANH_TOAN) {
            this.status = OrderStatus.CHUA_THANH_TOAN;
        }
    }
}

