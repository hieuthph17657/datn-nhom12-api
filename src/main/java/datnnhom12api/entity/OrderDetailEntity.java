package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.OrderDetailRequest;
import datnnhom12api.utils.support.OrderDetailStatus;
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
//    private Long productId;

    private double total;
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderDetailStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    public void setData(OrderDetailRequest request) {
//        this.productId = request.getProductId();
        this.quantity = request.getQuantity();
        if (request.getStatus().equals(" ")) {
            this.status = OrderDetailStatus.CHO_XAC_NHAN;
        } else if (request.getStatus().equals("Chờ lấy hàng")) {
            this.status = OrderDetailStatus.CHO_LAY_HANG;
        } else if (request.getStatus().equals("Đang giao")) {
            this.status = OrderDetailStatus.DANG_GIAO;
        } else if (request.getStatus().equals("Đã nhận")) {
            this.status = OrderDetailStatus.DA_NHAN;
        } else if (request.getStatus().equals("Đã huỷ")) {
            this.status = OrderDetailStatus.DA_HUY;
        }
        this.total = request.getTotal();
    }
}
