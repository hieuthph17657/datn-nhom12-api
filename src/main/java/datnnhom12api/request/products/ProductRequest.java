package datnnhom12api.request.products;

import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.entity.products.ConfigurationEntity;
import datnnhom12api.entity.products.ImagesEntity;
import lombok.*;

import javax.persistence.Column;
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

    @NotBlank(message = "Không được để trống")
    private String imei;
    @NotBlank(message = "Không được để trống")
    private Integer weight;
    @NotBlank(message = "Không được để trống")
    private String size;
    @NotBlank(message = "Không được để trống")
    private String debut;
    @NotBlank(message = "Không được để trống")
    private String p_n;
    @NotBlank(message = "Không được để trống")
    private String origin;
    @NotBlank(message = "Không được để trống")


    @NotNull(message = "Hình ảnh không được trống")
    private List<ImagesEntity> images;

    @NotNull(message = "Cấu hình không được để trống")
    private ConfigurationEntity configuration;
    @NotNull(message = "Người tạo không được để trống")
    private UserEntity createdBy;
    @NotNull(message = "Người cập nhật không được để trống")
    private UserEntity updatedBy;




}
