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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public CategoryDTO(CategoryEntity entity) {
        this.id = entity.getId();
        this.name=entity.getName();
        this.active=entity.getActive();
        this.createdAt=entity.getCreatedAt();
        this.updatedAt=entity.getUpdatedAt();
    }

    public CategoryEntity convertCategoryDTO(CategoryDTO dto) {
        CategoryEntity cate= new CategoryEntity();
        cate.setId(dto.getId());
        cate.setName(dto.getName());
        cate.setActive(dto.getActive());
        cate.setCreatedAt(dto.getCreatedAt());
        cate.setUpdatedAt(dto.getUpdatedAt());
        return cate;
    }
}

