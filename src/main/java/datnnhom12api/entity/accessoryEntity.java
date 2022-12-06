package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.accessoryRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accessories")
@EqualsAndHashCode(callSuper = true)
public class accessoryEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name="category_id")
    private Long  categoryId;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "accessory")
    private List<accessoryProductEntity> accessoryProducts;

    public void setData(accessoryRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.categoryId = request.getCategoryId();
        this.description = request.getDescription();
    }
}
