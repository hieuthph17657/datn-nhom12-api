package datnnhom12api.entity.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="designs")
public class DesignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "size")
    private String size;
    @Column(name = "meterial")
    private String meterial;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "color")
    private String color;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;



}
