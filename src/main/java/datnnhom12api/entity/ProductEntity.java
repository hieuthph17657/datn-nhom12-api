package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.ProductRequest;
import datnnhom12api.utils.support.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Builder
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

    @Column(name = "length")
    private Float length;

    @Column(name = "width")
    private Float width;

    @Column(name = "height")
    private Float height;

    @Column(name = "debut")
    private String debut;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private UserEntity updatedBy;

    @OneToMany(mappedBy = "product")
    private List<ImagesEntity> images;

    @OneToMany(mappedBy = "product")
    private List<ProductColorEntity> productColors;

    @OneToMany(mappedBy = "product")
    private List<accessoryProductEntity> accessoryProducts;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name="id_discount")
    private DiscountEntity discount;

    @ManyToOne
    @JoinColumn(name="manufacture_id")
    private ManufactureEntity manufacture;

    @ManyToOne
    @JoinColumn(name="processor_id")
    private ProcessorEntity processor;

    @ManyToOne
    @JoinColumn(name="ram_id")
    private RamEntity ram;

    @ManyToOne
    @JoinColumn(name="screen_id")
    private ScreenEntity screen;

    @ManyToOne
    @JoinColumn(name="card_id")
    private CardEntity card;

    @ManyToOne
    @JoinColumn(name="origin_id")
    private OriginEntity origin;

    @ManyToOne
    @JoinColumn(name="storage_id")
    private StorageEntity storage;

    @ManyToOne
    @JoinColumn(name="battery_id")
    private BatteryChargerEntity battery;

    @OneToMany(mappedBy = "product")
    private List<OrderDetailEntity> orderDetailEntities;

    private String win;

    private String material;

    @ManyToOne
    @JoinColumn(name="card_onboard")
    private CardEntity cardOnboard;

    private String security;

    private String description;

    public void setData(ProductRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
        this.weight = request.getWeight();
        this.debut = request.getDebut();
        this.length = request.getLength();
        this.height = request.getHeight();
        this.width = request.getWidth();
        this.status = request.getStatus() == ProductStatus.DRAFT ? ProductStatus.DRAFT : ProductStatus.ACTIVE;
        this.imei = request.getImei();
        this.win = request.getWin();
        this.material = request.getMaterial();
        this.security = request.getSecurity();
        this.description = request.getDescription();
    }

    public void enrichListImage(List<ImagesEntity> imagesEntities) {
        images = imagesEntities;
    }


}

