package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.*;
import datnnhom12api.utils.support.ProductStatus;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends BaseDTO implements Serializable {
    private Long id;

    private String name;

    private Integer quantity;

    private Double price;

    private String imei;

    private Float weight;

    private Float height ;

    private Float width;

    private Float length;

    private String debut;

    private String win;

    private String material;

    private UserEntity created_by;

    private UserEntity updated_by;

    private Long categoryId;

    private Long manufactureId;

    private List<ImagesEntity> images;

    private ProductStatus status;

    private ProcessorEntity processorId;

    private RamEntity RamId;

    private ScreenEntity screenId;

    private CardEntity cardId;

    private OriginEntity originId;

    private ColorEntity colorId;

    private Long batteryId;
}
