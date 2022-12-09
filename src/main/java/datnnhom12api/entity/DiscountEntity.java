package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.DiscountRequest;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "discounts")
public class DiscountEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="ratio")
    private Double ratio;
    @Column(name="start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @Column(name="end_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    @Column(name="active")
    private int active;

    @OneToMany(mappedBy = "discount")
    private List<ProductEntity> products;
//    @ManyToOne
//    @JoinColumn(name="id_product")
//    private ProductEntity product;

    public void setData(DiscountRequest request) {
        this.name = request.getName();
        this.ratio=request.getRatio();
        this.startDate=request.getStartDate();
        this.endDate=request.getEndDate();
        this.active = request.getActive();
    }
}
