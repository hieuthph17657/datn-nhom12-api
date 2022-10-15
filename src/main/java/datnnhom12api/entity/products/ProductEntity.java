package datnnhom12api.entity.products;
import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.request.products.ImagesRequest;
import datnnhom12api.request.products.ProductRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "status")
    private int status;

    @Column(name = "imei")
    private String imei;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "size")
    private String size;

    @Column(name = "debut")
    private String debut;

    @Column(name = "p_n")
    private String p_n;

    @Column(name = "origin")
    private String origin;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private UserEntity updatedBy;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ImagesEntity> images;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


    @OneToOne
    @JoinColumn(name = "configuration")
    private ConfigurationEntity configuration;

    public void setData(ProductRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
        this.category = request.getCategory();
        this.images = request.getImages();
        this.status = request.getStatus();
        this.configuration = request.getConfiguration();
        this.imei = request.getImei();
        this.createdBy = request.getCreatedBy();
        this.updatedBy = request.getUpdatedBy();

    }
}

