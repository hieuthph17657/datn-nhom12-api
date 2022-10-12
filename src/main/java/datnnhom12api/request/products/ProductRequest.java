package datnnhom12api.request.products;

import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.entity.products.ConfigurationEntity;
import datnnhom12api.entity.products.DesignEntity;
import datnnhom12api.entity.products.ImagesEntity;
import datnnhom12api.entity.products.SpeicificationEntity;
import lombok.*;

import javax.persistence.OneToMany;
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
    @NotBlank(message = "Mã loại sản phẩm không được để trống")
    private CategoryEntity category;
    @NotNull(message = "Trạng thái không được để trống")
    private int status;

    private String imei;

    private List<ImagesEntity> images;

    private DesignEntity design;
    private ConfigurationEntity configuration;
    private SpeicificationEntity speicification;
    private UserEntity createdBy;
    private UserEntity updatedBy;




}
