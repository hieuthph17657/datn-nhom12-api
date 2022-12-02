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

    private Long categoryId;

    @ManyToOne
    @JoinColumn(name="id_discount")
    private DiscountEntity discount;

    private Long manufactureId;

    private Long processorId;

    private Long ramId;

    private Long screenId;

    private Long cardId;

    private Long originId;

    private Long colorId;

    private Long batteryId;

    private String win;

    private String material;




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
        this.colorId = request.getColorId();
        this.cardId = request.getCardId();
        this.originId = request.getOriginId();
        this.batteryId = request.getBatteryId();
        this.screenId = request.getScreenId();
        this.ramId = request.getRamId();
        this.processorId = request.getProcessorId();
        this.win = request.getWin();
        this.material = request.getMaterial();
    }

    public void enrichListImage(List<ImagesEntity> imagesEntities) {
        images = imagesEntities;
    }


}

