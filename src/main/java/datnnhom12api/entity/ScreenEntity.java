package datnnhom12api.entity;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.ScreenRequest;
import datnnhom12api.utils.support.RamStatus;
import datnnhom12api.utils.support.ScreenStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "screens")
@EqualsAndHashCode(callSuper = true)
public class ScreenEntity  extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "size")
    private String size;

    @Column(name = "screen_technology")
    private String screenTechnology;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "screnn_type")
    private String screenType;

    @Column(name = "background_panel")
    private String backgroundPanel;

    @Column(name = "brightness")
    private String brightness;

    @Column(name = "color_coverage")
    private String colorCoverage;

    @Column(name = "touch_screen")
    private String touchScreen;

    @Column(name = "price")
    private double price;

    @Enumerated(EnumType.STRING)
    private ScreenStatus status;

    public void setData(ScreenRequest request) {
        this.price =request.getPrice();
        this.touchScreen = request.getTouchScreen();
        this.colorCoverage = request.getColorCoverage();
        this.brightness = request.getBrightness();
        this.backgroundPanel = request.getBackgroundPanel();
        this.screenType = request.getScreenType();
        this.resolution = request.getResolution();
        this.screenTechnology = request.getScreenTechnology();
        this.size = request.getSize();
        this.status = request.getStatus()
                == ScreenStatus.DRAFT ? ScreenStatus.DRAFT
                : ScreenStatus.ACTIVE ;
    }
}