package datnnhom12api.dto;

import datnnhom12api.entity.CategoryEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardDTO implements Serializable {

    private Long id;
    private String trandemark;
    private String model;
    private String memory;
    private Double price;
    private CategoryDTO category;
}
