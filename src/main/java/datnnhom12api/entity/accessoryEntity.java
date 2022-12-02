package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.accessoryRequest;
import lombok.*;

import javax.persistence.*;

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

    @Column(name="type")
    private String type;

    @Column(name="description")
    private String description;

    public void setData(accessoryRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.type = request.getType();
        this.description = request.getDescription();
    }
}
