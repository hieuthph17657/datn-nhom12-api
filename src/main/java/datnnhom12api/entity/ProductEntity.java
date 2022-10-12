package datnnhom12api.entity;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.ProductRequest;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private int quantity;
    private float price;
    private int imei;
    private String created_by;
    private String update_by;
    private Long categoryId;
    private int status;

    public void setData(ProductRequest request) {
        this.name = request.getName();
        this.quantity = request.getQuantity();
        this.price = request.getPrice();
//        this.categoryId = request.getCategoryId();
        this.status = request.getStatus();
    }
}

