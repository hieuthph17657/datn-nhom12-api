package datnnhom12api.entity;
import datnnhom12api.dto.core.BaseEntity;
import datnnhom12api.request.CategoryRequest;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_category")
@EqualsAndHashCode(callSuper = true)
public class CategoryEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="active")
    private int active;
    public void setData(CategoryRequest request) {
        this.name = request.getName();
        this.active = request.getActive();
    }
}

