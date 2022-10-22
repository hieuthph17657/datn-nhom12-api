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

    private Integer weight;

    private String size;

    private String debut;

    private String p_n;

    private String origin;

    private UserEntity created_by;

    private UserEntity updated_by;

    private Long categoryId;

    private Long manufactureId;

    private ConfigurationEntity configuration;

//    private List<ImagesEntity> images;
//
//    private List<ProductPropertyEntity> productProperties;

    private ProductStatus status;
}
