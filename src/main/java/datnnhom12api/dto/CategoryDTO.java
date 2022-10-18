package datnnhom12api.dto;

import datnnhom12api.utils.support.CategoryStatus;
import lombok.*;
import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO implements Serializable {

    private Long id;

    private String name;

    private CategoryStatus status;
}

