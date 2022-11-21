package datnnhom12api.request;

import datnnhom12api.entity.*;
import datnnhom12api.utils.support.ProductStatus;
import lombok.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Số lượng sản phẩm không được để trống")
    private Integer quantity;

    @NotNull(message = "Giá sản phẩm không được để trống")
    private Double price;

    @NotBlank(message = "Không được để trống")
    private String imei;

    @NotNull(message = "Không được để trống")
    private Float weight;

    @NotNull(message = "Không được để trống chiều dài")
    private Float length;

    @NotNull(message = "Không được để trống chiều rộng")
    private Float width;

    @NotNull(message = "Không được để trống chiều cao")
    private Float height;

    @NotBlank(message = "Năm ra mắt Không được để trống")
    private String debut;

    @NotBlank(message = "Không được để trống")
    private String p_n;

    @NotBlank(message = "Không được để trống")
    private String origin;

    private List<ImageRequest> images;
//    private List<String> images;

    //private ConfigurationEntity configuration;

    private UserEntity createdBy;

    private UserEntity updatedBy;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private Long categoryId;

    private Long manufactureId;

    private ConfigurationEntity configuration;

//    @NotEmpty(message = "PRODUCT_PROPERTIES_REQUIRED")
//    @Size(min = 1, message = "PRODUCT_PROPERTIES_SIZE")
//    private List<ProductPropertyEntity> productProperties;
}
