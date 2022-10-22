package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.utils.support.ManufactureStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "manufacture")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ManufactureEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private ManufactureStatus status;
}
