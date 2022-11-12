package datnnhom12api.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "return_detail")
@EqualsAndHashCode(callSuper = true)
public class ReturnDetailEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long productId;

//    private Long returnId;

    private int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "return_id")
    ReturnEntity Return;

}
