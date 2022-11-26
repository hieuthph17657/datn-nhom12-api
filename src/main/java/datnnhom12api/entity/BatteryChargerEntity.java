package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.BatteryChargerRequest;
import datnnhom12api.utils.support.BatteryChargerStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "battery_charger")
@EqualsAndHashCode(callSuper = true)
public class BatteryChargerEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="battery")
    private String battery;//pin

    @Column(name="charger")
    private String charger;//sac

    @Column(name = "price")
    private double price;

    @Column(name = "category_id")
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    private BatteryChargerStatus status;

    public void setData(BatteryChargerRequest request) {
        this.id = request.getId();
        this.battery = request.getBattery();
        this.charger = request.getCharger();
        this.price= request.getPrice();
        this.categoryId= request.getCategoryId();
        this.status = request.getStatus()
                == BatteryChargerStatus.DRAFT ? BatteryChargerStatus.DRAFT
                : request.getStatus()
                == BatteryChargerStatus.INACTIVE ? BatteryChargerStatus.INACTIVE
                :BatteryChargerStatus.ACTIVE ;
    }
}
