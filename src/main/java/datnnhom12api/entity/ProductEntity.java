package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.ProductRequest;
import datnnhom12api.utils.support.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "imei")
    private String imei;

    @Column(name = "weight")
    private Float weight;

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

//    @OneToMany(mappedBy = "product")
//    private List<ImagesEntity> images;

//    @OneToMany(mappedBy = "product")
//    private List<ProductPropertyEntity> productProperties;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "manufacture_id")
    private ManufactureEntity manufacture;

//   @OneToOne(mappedBy = "product")
//   private ConfigurationEntity configuration;

//    @OneToMany(mappedBy = "product")
//    List<OrderDetailEntity> orderDetails;

    public void setData(ProductRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
        this.weight = request.getWeight();
        this.debut = request.getDebut();
        this.size = request.getSize();
        this.p_n = request.getP_n();
        this.origin = request.getOrigin();
//        this.images = request.getImages();
        this.status = request.getStatus() == ProductStatus.DRAFT ? ProductStatus.DRAFT : ProductStatus.ACTIVE;
//        this.configuration = request.getConfiguration();
        this.imei = request.getImei();
        this.createdBy = request.getCreatedBy();
        this.updatedBy = request.getUpdatedBy();
//        System.out.println("request: "+request.getProductProperties());
//        this.productProperties = request.getProductProperties();
    }
}

