package datnnhom12api.entity.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "product_id")
    private Long product_id;
    @Column(name = "return_id")
    private Long return_id;
    @Column(name = "exchange_id")
    private Long exchange_id;
}
