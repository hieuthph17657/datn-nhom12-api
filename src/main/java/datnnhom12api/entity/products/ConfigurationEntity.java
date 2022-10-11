package datnnhom12api.entity.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "configurations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long product;
    @Column(name = "processor")
    private String processor;
    @Column(name = "ram")
    private String ram;
    @Column(name = "slot")
    private String slot;
    @Column(name = "battery")
    private String battery;
    @Column(name = "secutity")
    private String secutity;
    @Column(name = "screen")
    private String screen;
    @Column(name = "optical")
    private String optical;
    @Column(name = "hard_drive")
    private String hard_drive;
    @Column(name = "win")
    private String win;
    @Column(name = "capacity")
    private String capacity;



}
