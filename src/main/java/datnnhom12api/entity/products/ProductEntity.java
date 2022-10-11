package datnnhom12api.entity.products;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.products.ImagesRequest;
import datnnhom12api.request.products.ProductRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private float price;
    @Column(name = "imei")
    private String imei;
    @Column(name = "create_by")
    private Long createdBy;
    @Column(name = "update_by")
    private Long updateBy;
    @Column(name = "category_id")
    private Long category;
    private int status;
    @OneToMany(mappedBy = "product_id")
    private List<ImagesEntity> images;

//    @OneToOne
//    private SpeicificationEntity speicification;
//    @OneToOne(mappedBy = "product")
//    private DesignEntity design;
//    @OneToOne
//    private ConfigurationEntity configuration;

    public void setData(ProductRequest request) {
        this.name = request.getName();
        this.quantity = request.getQuantity();
        this.price = request.getPrice();
        this.category = request.getCategoryId();
        this.status = request.getStatus();
    }
}

