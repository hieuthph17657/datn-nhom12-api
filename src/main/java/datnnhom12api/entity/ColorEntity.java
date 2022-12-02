package datnnhom12api.entity;


import datnnhom12api.core.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "colors")
@EqualsAndHashCode(callSuper = true)
public class ColorEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="name")
    private String name;
}
