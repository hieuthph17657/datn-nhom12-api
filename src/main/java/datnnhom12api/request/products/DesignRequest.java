package datnnhom12api.request.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DesignRequest {
    @NotNull(message = "Không để trống!")
    private Long product_id;
    @NotBlank(message = "Không để trống!")
    private String size;
    @NotBlank(message = "Không để trống!")
    private String meterial;
    @NotNull(message = "Không để trống!")
    private Integer weight;
    @NotBlank(message = "Không để trống!")
    private String color;
}
