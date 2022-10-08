package datnnhom12api.dto;

import datnnhom12api.entity.CategoryEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO implements Serializable {
    private Long id;
    private String name;
    private int active;
}

