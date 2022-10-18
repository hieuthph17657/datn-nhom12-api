package datnnhom12api.dto;

import datnnhom12api.dto.core.BaseDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.entity.products.ConfigurationEntity;
import datnnhom12api.entity.products.ImagesEntity;
import datnnhom12api.entity.products.ProductPropertyEntity;
import datnnhom12api.utils.support.ProductStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    private CategoryEntity category;

    private ConfigurationEntity configuration;

    private List<ImagesEntity> images;

    private List<ProductPropertyEntity> productProperties;

    private ProductStatus status;
}
